import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { Proposal } from 'src/app/interfaces/Proposal';
import { SecurityService } from 'src/app/security/security.service';

@Injectable({
  providedIn: 'root'
})
export class UserProposalService {

  private apiURL_Proposal = "http://localhost:8080/api/v1/proposals";

  constructor( private httpClient: HttpClient, private securityService: SecurityService ) { }

  getAllProposals(): Promise<Proposal[]> {
    return new Promise((resolve) => {
      let userId: number = this.securityService.getUserId();
      this.httpClient.get<Proposal[]>(`${this.apiURL_Proposal}/from/${userId}`, this.securityService.getAuthHeader()).subscribe({
        next: (result: Proposal[]) => {
          resolve(result);
        },
        error: (error) => {
          console.error(error);
        }
      })
    }) 
  }

  getProposalByProposalId(proposalId: number): Promise<boolean> {
    return new Promise((resolve) => {
      this.httpClient.get<Proposal>(`${this.apiURL_Proposal}/proposal-id/${proposalId}`, this.securityService.getAuthHeader()).subscribe({
        next: (result: Proposal) => {
          if (result.proposalId != null)
            resolve(true);
          else
            resolve(false);
        },
        error: (error) => {
          console.error(error);
        }
      })
    })
  }

  editProposal(proposal: Proposal): Promise<boolean> {
    return new Promise((resolve) => {
      this.httpClient.put<boolean>(`${this.apiURL_Proposal}/edit`, proposal, this.securityService.getAuthHeader()).subscribe({
        next: (result: boolean) => {
          resolve(result);
        },
        error: (error) => {
          console.error(error);
        }
      })
    }) 
  }

  confirmProposal(proposalId: number, status: string): Promise<boolean> {
    return new Promise((resolve) => {
      this.httpClient.get<boolean>(`${this.apiURL_Proposal}/confirm/${status}/${proposalId}`, this.securityService.getAuthHeader()).subscribe({
        next: (result: boolean) => {
          resolve(result);
        },
        error: (error) => {
          console.error(error);
        }
      })
    }) 
  }

  deleteProposal(proposalId: number): Promise<boolean> {
    return new Promise((resolve) => {
      this.httpClient.delete<boolean>(`${this.apiURL_Proposal}/delete/${proposalId}`, this.securityService.getAuthHeader()).subscribe({
        next: (result: boolean) => {
          resolve(result);
        },
        error: (error) => {
          console.error(error);
        }
      })
    }) 
  }

  errorHandler(error: any) {

    let errorMessage = '';

    if(error.error instanceof ErrorEvent) {
      errorMessage = error.error.message;
    } else {
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }

    return throwError(() => errorMessage);
  }
}
