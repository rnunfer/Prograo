package com.prograo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProposalDTOImpl implements ProposalDTO {
    private Long proposalId;
    private String proposalTitle;
    private String proposalDescription;
    private String proposalEstimatedTime;
    private String proposalWorkStyle;
    private String proposalStatus;
    private Date proposalSendDate;
    private Date proposalConfirmDate;
    private Long userSeekerId;
    private String seekerName;
    private String seekerProfilePhoto;
    private String seekerTitle;
    private String seekerCity;
    private String seekerCountry;
    private String userFreelancerId;
    private String userFreelancerName;
}
