package com.prograo.controller;

import com.prograo.dto.ProjectDTO;
import com.prograo.dto.ProjectDTOImpl;
import com.prograo.service.ProjectService;
import com.prograo.service.SecurityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/projects")
public class ProjectController {

    private ProjectService projectService;

    @Autowired
    private SecurityService securityService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping(value = "/by/{userId}")
    public List<ProjectDTO> getAllProjectDTO(@RequestHeader("userAuthId") String userAuthId, @PathVariable("userId") Long userId) {
        List<ProjectDTO> result = new ArrayList<>();
        if (this.securityService.checkPermissions(userAuthId, "FS"))
            result = this.projectService.getAllProjectDTO(userId);
        return result;
    }

    @GetMapping(value = "/{projectId}")
    public ProjectDTOImpl getOneProjectDTO(@RequestHeader("userAuthId") String userAuthId, @PathVariable("projectId") Long projectId) {
        ProjectDTOImpl result = new ProjectDTOImpl();
        if (this.securityService.checkPermissions(userAuthId, "FS"))
            result = this.projectService.getOneProjectDTO(projectId, Long.valueOf(userAuthId));
        return result;
    }

    @PostMapping(value = "/edit")
    public boolean editProject(@RequestHeader("userAuthId") String userAuthId, @RequestBody ProjectDTOImpl projectDTO) {
        boolean result = false;
        projectDTO.setProjectContractPrice(Double.valueOf(projectDTO.getProjectContractPrice().toString()));
        if (this.securityService.checkPermissions(userAuthId, "FS"))
            result = this.projectService.editProject(projectDTO);
        return result;
    }

    @GetMapping(value = "/{projectId}/by-seeker/{userId}")
    public boolean signProjectBySeeker(@RequestHeader("userAuthId") String userAuthId, @PathVariable("projectId") Long projectId, @PathVariable("userId") Long userId) {
        boolean result = false;
        if (this.securityService.checkPermissions(userAuthId, "S"))
            result = this.projectService.signProject(projectId, userId, "seeker");
        return result;
    }

    @GetMapping(value = "/{projectId}/by-freelancer/{userId}")
    public boolean signProjectByFreelancer(@RequestHeader("userAuthId") String userAuthId, @PathVariable("projectId") Long projectId, @PathVariable("userId") Long userId) {
        boolean result = false;
        if (this.securityService.checkPermissions(userAuthId, "F"))
            result = this.projectService.signProject(projectId, userId, "freelancer");
        return result;
    }
}
