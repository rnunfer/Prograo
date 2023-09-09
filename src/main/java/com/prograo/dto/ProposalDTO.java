package com.prograo.dto;

import java.util.Date;

public interface ProposalDTO {

    Long getProposalId();
    String getProposalTitle();
    String getProposalDescription();
    String getProposalEstimatedTime();
    String getProposalWorkStyle();
    String getProposalStatus();
    Date getProposalSendDate();
    Date getProposalConfirmDate();
    Long getUserSeekerId();
    String getSeekerName();
    String getSeekerProfilePhoto();
    String getSeekerTitle();
    String getSeekerCity();
    String getSeekerCountry();
    String getUserFreelancerId();
    String getUserFreelancerName();

}
