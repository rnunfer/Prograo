package com.prograo.controller;

import com.prograo.domain.Skill;
import com.prograo.dto.FreelancerSkillDTO;
import com.prograo.dto.UserDTO;
import com.prograo.service.SecurityService;
import com.prograo.service.SkillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/skills")
public class SkillController {

    private final SkillService skillService;

    @Autowired
    private SecurityService securityService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @GetMapping
    public List<Skill> getAllSkills(@RequestHeader("userAuthId") String userAuthId) {
        List<Skill> result = new ArrayList<>();
        if (this.securityService.checkPermissions(userAuthId, "F"))
            result = this.skillService.getAllSkills();
        return result;
    }

    @GetMapping(value = "/{userId}")
    public List<FreelancerSkillDTO> getAllFreelancerSkill(@RequestHeader("userAuthId") String userAuthId, @PathVariable("userId") Long userId) {
        List<FreelancerSkillDTO> result = new ArrayList<>();
        if (this.securityService.checkPermissions(userAuthId, "FS"))
            result = this.skillService.getAllFreelancerSkill(userId);
        return result;
    }

    @GetMapping(value = "/add/{userId}/{skillId}")
    public boolean addSkillToFreelancer(@RequestHeader("userAuthId") String userAuthId, @PathVariable("userId") Long userId, @PathVariable("skillId") Long skillId) {
        boolean result = false;
        if (this.securityService.checkPermissions(userAuthId, "F", userId))
            result = this.skillService.addSkillToFreelancer(userId, skillId);
        return result;
    }

    @GetMapping(value = "/remove/{userId}/{skillId}")
    public boolean removeSkillOfFreelancer(@RequestHeader("userAuthId") String userAuthId, @PathVariable("userId") Long userId, @PathVariable("skillId") Long skillId) {
        boolean result = false;
        if (this.securityService.checkPermissions(userAuthId, "F", userId))
            result = this.skillService.removeSkillOfFreelancer(userId, skillId);
        return result;
    }

    @GetMapping(value = "/change-outstanding/{userId}/{skillId}")
    public boolean changeOutstandingSkillOfFreelancer(@RequestHeader("userAuthId") String userAuthId, @PathVariable("userId") Long userId, @PathVariable("skillId") Long skillId) {
        boolean result = false;
        if (this.securityService.checkPermissions(userAuthId, "F", userId))
            result = this.skillService.changeOutstandingSkillOfFreelancer(userId, skillId);
        return result;
    }

    @GetMapping(value = "/add-skill/{skillId}/to/{projectId}")
    public boolean addSkillToProject(@RequestHeader("userAuthId") String userAuthId, @PathVariable("skillId") Long skillId, @PathVariable("projectId") Long projectId ) {
        boolean result = false;
        if (this.securityService.checkPermissions(userAuthId, "FS"))
            result = this.skillService.addSkillToProject(skillId, projectId);
        return result;
    }

    @GetMapping(value = "/remove-skill/{skillId}/from/{projectId}")
    public boolean removeSkillFromProject(@RequestHeader("userAuthId") String userAuthId, @PathVariable("skillId") Long skillId, @PathVariable("projectId") Long projectId ) {
        boolean result = false;
        if (this.securityService.checkPermissions(userAuthId, "FS"))
            result = this.skillService.removeSkillFromProject(skillId, projectId);
        return result;
    }
}
