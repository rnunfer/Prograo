package com.prograo.dto;

import com.prograo.domain.Freelancer;
import com.prograo.domain.Location;
import com.prograo.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FreelancerDetails {

    private Long userId;

    private String userName;

    private String userProfilePhoto;

    private String userTitle;

    private String userCity;

    private String userCountry;

    private Long freelancerId;

    private int freelancerRate;

    private String freelancerDescription;

    private String freelancerTwitter;

    private String freelancerFacebook;

    private String freelancerEmail;

    private String freelancerLinkedin;

    private List<CalificationDTO> calificationList;

    private List<SkillUsed> skillList;

    private Double totalCalification;

    private Integer numberCalification;

    public void setFreelancerData(Freelancer freelancer) {
        this.freelancerId = freelancer.getId();
        this.freelancerRate = freelancer.getRate();
        this.freelancerDescription = freelancer.getDescription();
        this.freelancerTwitter = freelancer.getTwitter();
        this.freelancerFacebook = freelancer.getFacebook();
        this.freelancerEmail = freelancer.getEmail();
        this.freelancerLinkedin = freelancer.getLinkedin();
    }

    public void setUserData(User user) {
        this.userId = user.getId();
        this.userName = user.getName();
        this.userProfilePhoto = user.getProfilePhoto();
        this.userTitle = user.getTitle();
    }

    public void setLocationData(Location location) {
        this.userCity = location.getCity();
        this.userCountry = location.getCountry();
    }
}
