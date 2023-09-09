import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Freelancer } from 'src/app/interfaces/Freelancer';
import { FreelancerBox } from 'src/app/interfaces/FreelancerBox';
import { FreelancerDetails } from 'src/app/interfaces/FreelancerDetails';
import { SecurityService } from 'src/app/security/security.service';

@Injectable({
  providedIn: 'root'
})
export class FreelancerListService {

  private apiURL_Freelancer = "http://localhost:8080/api/v1/freelancers";
  private apiURL_Proposal = "http://localhost:8080/api/v1/proposals";

  constructor( private httpClient: HttpClient, private securityService: SecurityService ) {}

  getFreelancerByFreelancerId(freelancerId: number): Promise<FreelancerDetails> {
    return new Promise((resolve) => {
      this.httpClient.get<FreelancerDetails>(`${this.apiURL_Freelancer}/freelancer-details/${freelancerId}`, this.securityService.getAuthHeader()).subscribe({
        next: (result: FreelancerDetails) => {
          resolve(result);
        },
        error: (error) => {
          console.error(error);
        }
      })
    })
  }

  sendProposal(data: any): Promise<boolean> {
    return new Promise((resolve) => {
      console.log(data);
      let userId: number = this.securityService.getUserId();
      this.httpClient.post<boolean>(`${this.apiURL_Proposal}/send-proposal-by/${userId}`, data, this.securityService.getAuthHeader()).subscribe({
        next: (result: boolean) => {
          resolve(result);
        },
        error: (error) => {
          console.error(error);
        }
      })
    })
  }

  getAllFreelancerBoxs(): Promise<FreelancerBox[]> {
    return new Promise((resolve) => {
      this.httpClient.get<FreelancerBox[]>(`${this.apiURL_Freelancer}/freelancer-boxs`, this.securityService.getAuthHeader()).subscribe({
        next: (result: FreelancerBox[]) => {
          resolve(result);
        },
        error : (error) => {
          console.error(error);
        }
      })
    })
  }
  
}
