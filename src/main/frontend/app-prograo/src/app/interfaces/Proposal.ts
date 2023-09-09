export interface Proposal {
  proposalId: number;
  proposalTitle: string;
  proposalDescription: string;
  proposalEstimatedTime: string;
  proposalWorkStyle: string;
  proposalStatus: string;
  proposalSendDate: Date | null;
  proposalConfirmDate: Date | null;
  userSeekerId: number;
  seekerName: string;
  seekerProfilePhoto: string;
  seekerTitle: string;
  seekerCity: string;
  seekerCountry: string;
  userFreelancerId: number;
  userFreelancerName: string;
  [key: string]: any;
}
