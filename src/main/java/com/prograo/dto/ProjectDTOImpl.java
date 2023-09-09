package com.prograo.dto;

import com.prograo.domain.Skill;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectDTOImpl implements ProjectDTO{
    private Long projectId;
    private String projectTitle;
    private String projectDescription;
    private String projectWorkStyle;
    private String projectStatus;
    private Date projectDeadline;
    private Date projectSendDate;
    private Date projectStartDate;
    private Date projectFinishDate;
    private Double projectContractPrice;
    private Boolean projectSignedBySeeker;
    private Boolean projectSignedByFreelancer;
    private Long userSeekerId;
    private String userSeekerName;
    private String userSeekerProfilePhoto;
    private String userSeekerTitle;
    private String userSeekerCity;
    private String userSeekerCountry;
    private Long userFreelancerId;
    private String userFreelancerName;
    private String userFreelancerProfilePhoto;
    private String userFreelancerTitle;
    private String userFreelancerCity;
    private String userFreelancerCountry;
    private Long calificationId;
    private Double calificationNote;
    private String calificationDescription;
    private String calificationImage;
    private Date calificationDate;
    private Set<Skill> skillList;

    public void setValues(ProjectDTO projectDTO) {
        this.projectId = projectDTO.getProjectId();
        this.projectTitle = projectDTO.getProjectTitle();
        this.projectDescription = projectDTO.getProjectDescription();
        this.projectWorkStyle = projectDTO.getProjectWorkStyle();
        this.projectStatus = projectDTO.getProjectStatus();
        this.projectDeadline = projectDTO.getProjectDeadline();
        this.projectSendDate = projectDTO.getProjectSendDate();
        this.projectStartDate = projectDTO.getProjectStartDate();
        this.projectFinishDate = projectDTO.getProjectFinishDate();
        this.projectContractPrice = projectDTO.getProjectContractPrice();
        this.projectSignedBySeeker = projectDTO.getProjectSignedBySeeker();
        this.projectSignedByFreelancer = projectDTO.getProjectSignedByFreelancer();
        this.userSeekerId = projectDTO.getUserSeekerId();
        this.userSeekerName = projectDTO.getUserSeekerName();
        this.userSeekerProfilePhoto = projectDTO.getUserSeekerProfilePhoto();
        this.userSeekerTitle = projectDTO.getUserSeekerTitle();
        this.userSeekerCity = projectDTO.getUserSeekerCity();
        this.userSeekerCountry = projectDTO.getUserSeekerCountry();
        this.userFreelancerId = projectDTO.getUserFreelancerId();
        this.userFreelancerName = projectDTO.getUserFreelancerName();
        this.userFreelancerProfilePhoto = projectDTO.getUserFreelancerProfilePhoto();
        this.userFreelancerTitle = projectDTO.getUserFreelancerTitle();
        this.userFreelancerCity = projectDTO.getUserFreelancerCity();
        this.userFreelancerCountry = projectDTO.getUserFreelancerCountry();
        this.calificationId = projectDTO.getCalificationId();
        this.calificationNote = projectDTO.getCalificationNote();
        this.calificationDescription = projectDTO.getCalificationDescription();
        this.calificationImage = projectDTO.getCalificationImage();
        this.calificationDate = projectDTO.getCalificationDate();
    }
}
