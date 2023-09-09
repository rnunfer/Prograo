package com.prograo.service;

import com.prograo.domain.Project;
import com.prograo.domain.Proposal;
import com.prograo.domain.Skill;
import com.prograo.dto.ProjectDTO;
import com.prograo.dto.ProjectDTOImpl;
import com.prograo.repository.ProjectRepository;
import com.prograo.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private SkillRepository skillRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<ProjectDTO> getAllProjectDTO(Long userId) {
        List<ProjectDTO> projectDTOList = new ArrayList<>();
        String userType = this.userService.getUserType(userId);
        if(userType.equals("administrator")) {
            projectDTOList = this.projectRepository.getAllProjects();
        } else if(userType.equals("freelancer") || userType.equals("seeker")) {
            projectDTOList = this.projectRepository.getProjectsByUserId(userId);
        }
        return projectDTOList;
    }

    public ProjectDTOImpl getOneProjectDTO(Long projectId, Long userId) {
        ProjectDTO projectDTO = new ProjectDTOImpl();
        String userType = this.userService.getUserType(userId);
        if(userType.equals("administrator")) {
            projectDTO = this.projectRepository.getOneProject(projectId);
        } else if(userType.equals("freelancer") || userType.equals("seeker")) {
            projectDTO = this.projectRepository.getOneProjectByUserId(userId, projectId).get();
        }
        ProjectDTOImpl projectDTOImpl = new ProjectDTOImpl();
        projectDTOImpl.setValues(projectDTO);
        projectDTOImpl.setSkillList(this.skillRepository.getAllSkillsProject(projectId));
        return projectDTOImpl;
    }

    public void newProjectByProposal(Proposal proposal) {
        Project newProject = new Project();
        newProject.setName(proposal.getTitle());
        newProject.setDescription(proposal.getDescription());
        newProject.setWorkStyle(proposal.getWorkStyle());
        newProject.setContractPrice(0.0);
        newProject.setSendDate(proposal.getConfirmDate());
        newProject.setStatus("preproject");
        newProject.setSeeker(proposal.getSeeker());
        newProject.setFreelancer(proposal.getFreelancer());
        newProject.setSignedBySeeker(false);
        newProject.setSignedByFreelancer(false);
        this.projectRepository.save(newProject);
    }

    public boolean editProject(ProjectDTOImpl projectDTO) {
        boolean result = false;
        Optional<Project> projectOptional = this.projectRepository.findById(projectDTO.getProjectId());
        if (projectOptional.isPresent() && projectDTO.getProjectStatus().equals("preproject")) {
            Project project = projectOptional.get();
            project.setName(projectDTO.getProjectTitle());
            project.setDescription(projectDTO.getProjectDescription());
            project.setWorkStyle(projectDTO.getProjectWorkStyle());
            project.setContractPrice(projectDTO.getProjectContractPrice());
            project.setSignedByFreelancer(false);
            project.setSignedBySeeker(false);
            this.projectRepository.save(project);
            result = true;
        }
        return result;
    }

    public boolean signProject(Long projectId, Long userId, String userType) {
        boolean result = false;
        Optional<ProjectDTO> projectDTOOptional = this.projectRepository.getOneProjectByUserId(userId, projectId);
        if (projectDTOOptional.isPresent()) {
            Project project = this.projectRepository.findById(projectDTOOptional.get().getProjectId()).get();
            if (project.getContractPrice() >= 0 &&
                project.getName() != null &&
                project.getDescription() != null &&
                project.getDescription().length() <= 1000 &&
                project.getWorkStyle() != null
            ) {
                if (userType.equals("seeker"))
                    project.setSignedBySeeker(true);
                if (userType.equals("freelancer"))
                    project.setSignedByFreelancer(true);
                project = this.checkSign(project);
                result = true;
                this.projectRepository.save(project);
            }
        }
        return result;
    }

    public Project checkSign(Project project) {
        if (project.getSignedByFreelancer() && project.getSignedBySeeker()) {
            if (project.getStatus().equals("preproject")) {
                project.setStatus("inprogress");
                project.setStartDate(new Date());
                project.setSignedBySeeker(false);
                project.setSignedByFreelancer(false);
            } else if (project.getStatus().equals("inprogress")) {
                project.setStatus("finished");
                project.setFinishDate(new Date());
            }
        }
        return project;
    }

}
