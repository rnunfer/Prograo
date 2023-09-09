package com.prograo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prograo.dto.UserDTO;
import com.prograo.service.SecurityService;
import com.prograo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/users")
public class UserController {

    @Value("${upload.directory}")
    private String uploadDirectory;
    private final UserService userService;

    @Autowired
    private SecurityService securityService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/login")
    public UserDTO login(@RequestHeader("userAuthId") String userAuthId, @RequestBody Map<String, String> loginData) {
        UserDTO result = new UserDTO();
        if (this.securityService.checkPermissions(userAuthId, "G")) {
            String email = loginData.get("email");
            String password = loginData.get("password");
            result = this.userService.login(email, password);
        }
        return result;
    }

    @PostMapping(value = "/register")
    public UserDTO register(@RequestHeader("userAuthId") String userAuthId, @RequestBody Map<Object, String> registerData) {
        UserDTO result = new UserDTO();
        if (this.securityService.checkPermissions(userAuthId, "G")) {
            String email = registerData.get("email");
            String password = registerData.get("password");
            String userType = registerData.get("userType");
            result = this.userService.register(email, password, userType);
        }
        return result;
    }

    @GetMapping(value = "/{userId}")
    public UserDTO getUserById(@RequestHeader("userAuthId") String userAuthId, @PathVariable("userId") Long userId) {
        UserDTO result = new UserDTO();
        if (this.securityService.checkPermissions(userAuthId, "FS", userId)) {
            result = this.userService.getUserById(userId);
        }
        return result;
    }

    @PostMapping(value = "/edit")
    public boolean editUser(@RequestHeader("userAuthId") String userAuthId, @RequestBody UserDTO userToChange) {
        boolean result = false;
        if (this.securityService.checkPermissions(userAuthId, "FS", userToChange.getId())) {
            result = this.userService.editUser(userToChange);
        }
        return result;
    }

    @PostMapping(value = "/upload-profile-photo")
    public ResponseEntity<String> uploadProfilePhoto(@RequestParam("user") String userJson, @RequestParam("profilePhoto") MultipartFile file) {
        try {
            UserDTO userDTO = new ObjectMapper().readValue(userJson, UserDTO.class);

            // Check if the file exceeds the maximum size
            if (file.getSize() > 5242880) {
                throw new MaxUploadSizeExceededException(5242880);
            }

            // Generate a unique filename for the uploaded file
            String fileName = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();

            // Construct the destination path to save the file
            Path destination = Paths.get(uploadDirectory, fileName.replace("/", File.separator));

            // Save the file to the destination path
            Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);

            // Return the saved file path or unique identifier
            return ResponseEntity.ok(fileName);
        } catch (MaxUploadSizeExceededException e) {
            // Handle the maximum upload size exceeded exception
            return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body("Maximum upload size exceeded. Please upload a file up to 5 MB.");
        } catch (IOException e) {
            // Handle other IO exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload the file.");
        }
    }
}
