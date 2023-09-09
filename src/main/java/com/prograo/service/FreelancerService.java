package com.prograo.service;

import java.util.List;
import java.util.Optional;

import com.prograo.dto.FreelancerDetails;
import com.prograo.dto.FreelancerBox;
import com.prograo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prograo.domain.Freelancer;

@Service
public class FreelancerService {
	
	private final FreelancerRepository freelancerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private CalificationService calificationService;

    @Autowired
    private CalificationRepository calificationRepository;

    @Autowired
    private SkillRepository skillRepository;

	public FreelancerService(FreelancerRepository freelancerRepository) {
        this.freelancerRepository = freelancerRepository;
    }
	
	public List<Freelancer> all() {
        return this.freelancerRepository.findAll();
    }

    public List<FreelancerBox> allFreelancerBoxs() {
        return this.freelancerRepository.getAllFreelancerBoxes();
    }

    public FreelancerDetails getFreelancerDetails(Long freelancerId) {
        FreelancerDetails freelancerDetails = new FreelancerDetails();
        Optional<Freelancer> freelancerOptional = this.freelancerRepository.findById(freelancerId);
        if (freelancerOptional.isPresent()) {
            freelancerDetails.setFreelancerData(freelancerOptional.get());
            freelancerDetails.setUserData(this.userRepository.getUserByFreelancerId(freelancerId));
            freelancerDetails.setLocationData(this.locationRepository.getLocationByFreelancerId(freelancerId));
            freelancerDetails.setCalificationList(this.calificationService.getAllCalification(freelancerId));
            freelancerDetails.setSkillList(this.skillRepository.getFreelancerDetailsSkills(freelancerId));
            freelancerDetails.setTotalCalification(this.calificationRepository.getTotalCalificationFreelancer(freelancerId));
            freelancerDetails.setNumberCalification(this.calificationRepository.getNumberCalificationFreelancer(freelancerId));
        }
        return freelancerDetails;
    }

    public Freelancer getFreelancerByUserId(Long userId) {
        Freelancer freelancer = new Freelancer();
        Optional<Freelancer> freelancerOptional = this.freelancerRepository.getFreelancerByUserId(userId);
        if (freelancerOptional.isPresent())
            freelancer = freelancerOptional.get();
        return freelancer;
    }

    public boolean editFreelancer(Freelancer freelancer) {
        boolean result = false;
        Optional<Freelancer> freelancerOptional = this.freelancerRepository.findById(freelancer.getId());
        if (freelancerOptional.isPresent()) {
            Freelancer freelancerBeingChanged = freelancerOptional.get();
            freelancerBeingChanged.setRate(freelancer.getRate());
            freelancerBeingChanged.setDescription(freelancer.getDescription());
            freelancerBeingChanged.setTwitter(freelancer.getTwitter());
            freelancerBeingChanged.setFacebook(freelancer.getFacebook());
            freelancerBeingChanged.setLinkedin(freelancer.getLinkedin());
            freelancerBeingChanged.setEmail(freelancer.getEmail());
            this.freelancerRepository.save(freelancerBeingChanged);
            result = true;
        } return result;
    }
}
