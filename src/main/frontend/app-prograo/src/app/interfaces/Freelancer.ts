export interface Freelancer {
    id: number;
    name: string;
    profilePhoto: string;
    title: string;
    city: string;
    country: string;
    rate: number;
    twitter: string;
    facebook: string;
    email: string;
    linkedin: string;
    registrationDate: Date;
    description: string;
    [key: string]: any;
  }