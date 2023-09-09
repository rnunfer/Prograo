package com.prograo.dto;

import com.prograo.domain.Skill;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface ProjectDTO {
    Long getProjectId();
    String getProjectTitle();
    String getProjectDescription();
    String getProjectWorkStyle();
    String getProjectStatus();
    Date getProjectDeadline();
    Date getProjectSendDate();
    Date getProjectStartDate();
    Date getProjectFinishDate();
    Double getProjectContractPrice();
    Boolean getProjectSignedBySeeker();
    Boolean getProjectSignedByFreelancer();
    Long getUserSeekerId();
    String getUserSeekerName();
    String getUserSeekerProfilePhoto();
    String getUserSeekerTitle();
    String getUserSeekerCity();
    String getUserSeekerCountry();
    Long getUserFreelancerId();
    String getUserFreelancerName();
    String getUserFreelancerProfilePhoto();
    String getUserFreelancerTitle();
    String getUserFreelancerCity();
    String getUserFreelancerCountry();
    Long getCalificationId();
    Double getCalificationNote();
    String getCalificationDescription();
    String getCalificationImage();
    Date getCalificationDate();
    Set<Skill> getSkillList();
}
