package com.prograo.service;

import com.prograo.domain.Freelancer;
import com.prograo.domain.Location;
import com.prograo.domain.Seeker;
import com.prograo.domain.User;
import com.prograo.dto.UserDTO;
import com.prograo.repository.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Optional;

@Service
public class UserService {

    @PersistenceContext
    private EntityManager entityManager;

    private final UserRepository userRepository;
    private final LocationRepository locationRepository;
    private final FreelancerRepository freelancerRepository;
    private final SeekerRepository seekerRepository;
    private final AdministratorRepository administratorRepository;

    private final SkillRepository skillRepository;

    public UserService(UserRepository userRepository, LocationRepository locationRepository, FreelancerRepository freelancerRepository, SeekerRepository seekerRepository, AdministratorRepository administratorRepository, SkillRepository skillRepository) {
        this.userRepository = userRepository;
        this.locationRepository = locationRepository;
        this.freelancerRepository = freelancerRepository;
        this.seekerRepository = seekerRepository;
        this.administratorRepository = administratorRepository;
        this.skillRepository = skillRepository;
    }

    public UserDTO fromUserToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setTitle(user.getTitle());
        userDTO.setProfilePhoto(user.getProfilePhoto());
        userDTO.setStatus(user.getStatus());
        userDTO.setVerified(user.getVerified());
        if (user.getLocation() != null) {
            userDTO.setCity(user.getLocation().getCity());
            userDTO.setCountry(user.getLocation().getCountry());
        }
        userDTO.setUserType(this.getUserType(userDTO.getId()));
        return userDTO;
    }

    public String getUserType(Long userId) {
        String userType = "guest";
        Optional<User> userOptional = this.userRepository.findById(userId);
        if(userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getFreelancer() != null) {
                userType = "freelancer";
            } else if (user.getSeeker() != null) {
                userType = "seeker";
            } else if (user.getAdministrator() != null) {
                userType = "administrator";
            }
        }
        return userType;
    }

    public UserDTO login(String email, String password) {
        UserDTO userLogin = new UserDTO();
        Optional<User> userOptional = this.userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (isPasswordMatch(password, user.getPassword())) {
                userLogin = fromUserToUserDTO(user);
            }
        }
        return userLogin;
    }

    @Transactional
    public UserDTO register(String email, String password, String userType) {
        UserDTO userRegister = new UserDTO();
        if (userType.equals("freelancer") || userType.equals("seeker")) {
            User user = new User();
            user.setEmail(email);
            String hashedPassword = hashPassword(password);
            user.setPassword(hashedPassword);
            user.setProfilePhoto("default.png");
            user.setStatus("inactive");
            user.setVerified(false);
            user.setRegistrationDate(new Date());
            switch (userType) {
                case "freelancer":
                    Freelancer freelancer = new Freelancer();
                    freelancer.setUser(user);
                    freelancer.setRate(0);
                    entityManager.persist(freelancer);
                    user.setFreelancer(freelancer);
                    break;
                case "seeker":
                    Seeker seeker = new Seeker();
                    seeker.setUser(user);
                    entityManager.persist(seeker);
                    user.setSeeker(seeker);
                    break;
            }
            user = this.userRepository.save(user);
            userRegister = this.fromUserToUserDTO(user);
        }
        return userRegister;
    }

    public UserDTO getUserById(Long userId) {
        UserDTO userDTO = new UserDTO();
        Optional<User> userOptional = this.userRepository.findById(userId);
        if (userOptional.isPresent()) {
            userDTO = this.fromUserToUserDTO(userOptional.get());
        }
        return userDTO;
    }

    public boolean editUser(UserDTO userDTO) {
        boolean result = false;
        Optional<User> userOptional = this.userRepository.findById(userDTO.getId());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setName(userDTO.getName());
            user.setTitle(userDTO.getTitle());
            user.setProfilePhoto(userDTO.getProfilePhoto());
            Optional<Location> locationOptional = this.locationRepository.findByNameAndCity(userDTO.getCity(), userDTO.getCountry());
            if (locationOptional.isPresent()) {
                user.setLocation(locationOptional.get());
            }
            user.setStatus(this.setUserStatus(user));
            this.userRepository.save(user);
            result = true;
        }
        return result;
    }

    public String setUserStatus(User user) {
        String status = "inactive";
        if (user.getStatus().equals("suspended")) {
            status = "suspended";
        } else {
            if (user.getName() != null && user.getLocation() != null && user.getTitle() != null && user.getProfilePhoto() != null) {
                String userType = this.getUserType(user.getId());
                if (userType.equals("freelancer")) {
                    Freelancer freelancer = this.freelancerRepository.getFreelancerByUserId(user.getId()).get();
                    if (freelancer.getDescription() != null &&
                            freelancer.getDescription().length() < 1000 &&
                            freelancer.getRate() >= 0
                    ) {
                        Integer c1 = this.skillRepository.countSkillsByFreelancerId(freelancer.getId());
                        Integer c2 = this.skillRepository.countOutstandingSkillsByFreelancerId(freelancer.getId());
                        if (c1 != null && c2 != null)
                            status = "active";
                    }
                } else if (userType.equals("seeker") || userType.equals("administrator")){
                    status = "active";
                }
            }
        }
        return status;
    }

    private boolean isPasswordMatch(String rawPassword, String hashedPassword) {
        String hashedRawPassword = hashPassword(rawPassword);
        return hashedRawPassword.equals(hashedPassword);
    }

    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(hashedBytes);
        } catch (NoSuchAlgorithmException e) {
            // Handle the exception appropriately
        }
        return null;
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder hexStringBuilder = new StringBuilder();
        for (byte b : bytes) {
            String hexString = Integer.toHexString(0xff & b);
            if (hexString.length() == 1) {
                hexStringBuilder.append('0');
            }
            hexStringBuilder.append(hexString);
        }
        return hexStringBuilder.toString();
    }

}
