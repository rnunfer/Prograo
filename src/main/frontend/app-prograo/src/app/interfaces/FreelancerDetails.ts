import { CalificationDTO } from "./CalificationDTO";
import { FreelancerDetailsSkills } from "./FreelancerDetailsSkills";
import { FreelancerSkill } from "./FreelancerSkill";

export interface FreelancerDetails {
    userId: number;
    userName: string;
    userProfilePhoto: string;
    userTitle: string;
    userCity: string;
    userCountry: string;
    freelancerId: number;
    freelancerRate: number;
    freelancerDescription: string;
    freelancerTwitter: string;
    freelancerFacebook: string;
    freelancerEmail: string;
    freelancerLinkedin: string;
    calificationList: CalificationDTO[];
    skillList: FreelancerDetailsSkills[];
    totalCalification: number;
    numberCalification: number;
    [key: string]: any;
}