package com.prograo.controller;

import com.prograo.domain.Freelancer;
import com.prograo.dto.FreelancerDetails;
import com.prograo.dto.SkillUsed;
import com.prograo.dto.FreelancerBox;
import com.prograo.service.FreelancerService;

import java.util.ArrayList;
import java.util.List;

import com.prograo.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/freelancers")
public class FreelancerController {
	
	private final FreelancerService freelancerService;

    @Autowired
    private SecurityService securityService;

	public FreelancerController(FreelancerService freelancerService) {
        this.freelancerService = freelancerService;
    }

    @GetMapping(value = "/freelancer-boxs")
    public List<FreelancerBox> allFreelancerBoxs(@RequestHeader("userAuthId") String userAuthId) {
        List<FreelancerBox> list = new ArrayList<>();
        if (this.securityService.checkPermissions(userAuthId, "FSG"))
            list = this.freelancerService.allFreelancerBoxs();
        return list;
    }

    @GetMapping(value = "/freelancer-details/{freelancerId}")
    public FreelancerDetails getFreelancerDetailsByFreelancerId(@RequestHeader("userAuthId") String userAuthId, @PathVariable("freelancerId") Long freelancerId) {
        FreelancerDetails result = new FreelancerDetails();
        if (this.securityService.checkPermissions(userAuthId, "FSG"))
            result = this.freelancerService.getFreelancerDetails(freelancerId);
        return result;
    }

    @GetMapping(value = "/{userId}")
    public Freelancer getFreelancerByUserId(@RequestHeader("userAuthId") String userAuthId, @PathVariable("userId") Long userId) {
        Freelancer result = new Freelancer();
        if (this.securityService.checkPermissions(userAuthId, "F", userId))
            result = this.freelancerService.getFreelancerByUserId(userId);
        return result;
    }

    @PostMapping("/edit/{freelancerId}")
    public boolean editFreelancer(@RequestHeader("userAuthId") String userAuthId, @RequestBody Freelancer freelancer, @PathVariable("freelancerId") Long freelancerId) {
        boolean result = false;
        if (this.securityService.checkPermissions(userAuthId, "F")) {
            result = this.freelancerService.editFreelancer(freelancer);
        }
        return result;
    }

}
