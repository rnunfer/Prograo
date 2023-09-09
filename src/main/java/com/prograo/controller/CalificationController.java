package com.prograo.controller;

import com.prograo.domain.Calification;
import com.prograo.domain.Freelancer;
import com.prograo.dto.CalificationDTO;
import com.prograo.service.CalificationService;
import com.prograo.service.SecurityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/califications")
public class CalificationController {

    private final CalificationService calificationService;

    @Autowired
    private SecurityService securityService;

    public CalificationController (CalificationService calificationService) {
        this.calificationService = calificationService;
    }

    @PostMapping(value = "/set-calification/{projectId}")
    public void sendCalification(@RequestHeader("userAuthId") String userAuthId, @RequestBody Map<Object, String> data, @PathVariable("projectId") Long projectId) {
        if (this.securityService.checkPermissions(userAuthId, "S")) {
            this.calificationService.sendCalification(data, projectId);
        }
    }
}
