package com.prograo.service;

import com.prograo.domain.User;
import com.prograo.dto.UserDTO;
import com.prograo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SecurityService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    public boolean checkPermissions(String userId, String securityLevel) {
        boolean result = false;
        String userType = this.userService.getUserType(Long.valueOf(userId));
        if (userType.equals("administrator")) {
            result = true;
        } else {
            switch(securityLevel) {
                case "FSG":
                    if (userType.equals("freelancer") || userType.equals("seeker") || userType.equals("guest"))
                        result = true;
                    break;
                case "FS":
                    if (userType.equals("freelancer") || userType.equals("seeker"))
                        result = true;
                    break;
                case "F":
                    if (userType.equals("freelancer"))
                        result = true;
                    break;
                case "S":
                    if (userType.equals("seeker"))
                        result = true;
                    break;
                case "G":
                    if (userType.equals("guest"))
                        result = true;
                    break;
            }
        }
        return result;
    }

    public boolean checkPermissions(String userId, String securityLevel, Long userBeingChangedId) {
        boolean result = false;
        String userType = this.userService.getUserType(Long.valueOf(userId));
        if (Long.valueOf(userId) == userBeingChangedId) {
            if (userType.equals("administrator")) {
                result = true;
            } else {
                switch(securityLevel) {
                    case "FSG":
                        if (userType.equals("freelancer") || userType.equals("seeker") || userType.equals("guest"))
                            result = true;
                        break;
                    case "FS":
                        if (userType.equals("freelancer") || userType.equals("seeker"))
                            result = true;
                        break;
                    case "F":
                        if (userType.equals("freelancer"))
                            result = true;
                        break;
                    case "S":
                        if (userType.equals("seeker"))
                            result = true;
                        break;
                    case "G":
                        if (userType.equals("guest"))
                            result = true;
                        break;
                }
            }
        }
        return result;
    }
}
