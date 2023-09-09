import { FreelancerSkill } from "./FreelancerSkill";
import { Skill } from "./Skill";

export interface Project {
    projectId: number;
    projectTitle: string;
    projectDescription: string;
    projectWorkStyle: string;
    projectStatus: string;
    projectDeadline: Date | null;
    projectSendDate: Date | null;
    projectStartDate: Date | null;
    projectFinishDate: Date | null;
    projectContractPrice: number;
    projectSignedBySeeker: boolean;
    projectSignedByFreelancer: boolean;
    userSeekerId: number;
    userSeekerName: string;
    userSeekerProfilePhoto: string;
    userSeekerTitle: string;
    userSeekerCity: string;
    userSeekerCountry: string;
    userFreelancerId: number;
    userFreelancerName: string;
    userFreelancerProfilePhoto: string;
    userFreelancerTitle: string;
    userFreelancerCity: string;
    userFreelancerCountry: string;
    calificationId: number;
    calificationNote: number;
    calificationDescription: string;
    calificationImage: string;
    calificationDate: Date | null;
    skillList: Skill[];
    [key: string]: any;
}
