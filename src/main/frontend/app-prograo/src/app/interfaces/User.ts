export interface User {
  id: number;
  name: string;
  email: string;
  title: string;
  profilePhoto: string;
  status: string;
  verified: boolean;
  city: string;
  country: string;
  userType: string;
  [key: string]: any;
}
