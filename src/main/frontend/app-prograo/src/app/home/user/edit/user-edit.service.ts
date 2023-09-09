import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { FreelancerSkill } from 'src/app/interfaces/FreelancerSkill';
import { Skill } from 'src/app/interfaces/Skill';
import { SecurityService } from 'src/app/security/security.service';
import { Location } from 'src/app/interfaces/Location';
import { User } from 'src/app/interfaces/User';
import { Freelancer } from 'src/app/interfaces/Freelancer';

@Injectable({
  providedIn: 'root'
})
export class UserEditService {

  private apiURL_User = "http://localhost:8080/api/v1/users";
  private apiURL_Freelancer = "http://localhost:8080/api/v1/freelancers";
  private apiURL_Skill = "http://localhost:8080/api/v1/skills";
  private apiURL_Location = "http://localhost:8080/api/v1/locations";

  constructor( private httpClient: HttpClient, private securityService: SecurityService ) { }

  /* USER */

  editUser(userToChange: User): Promise<boolean> {
    return new Promise((resolve) => {
      this.httpClient.post<boolean>(`${this.apiURL_User}/edit`, userToChange, this.securityService.getAuthHeader()).subscribe({
        next: (result: boolean) => {
          if (userToChange.id == this.securityService.getUserId())
            this.securityService.updateUser();
          resolve(result);
        },
        error: (error) => {
          console.error(error);
        }
      });
    });
  }

  uploadProfilePhoto(formData: FormData): Observable<string> {
    let user: User = this.securityService.getUser();
    formData.append('user', JSON.stringify(user));
    return this.httpClient.post<string>(`${this.apiURL_User}/upload-profile-photo`, formData);
  }

  /* FREELANCER */

  getFreelancerByUserId(userId: number): Promise<Freelancer> {
    return new Promise((resolve) => {
      this.httpClient.get<Freelancer>(`${this.apiURL_Freelancer}/${userId}`, this.securityService.getAuthHeader()).subscribe({
        next: (result: Freelancer) => {
          resolve(result);
        },
        error: (error) => {
          console.error(error);
        }
      });
    });
  }

  editFreelancer(freelancer: Freelancer): Promise<boolean> {
    return new Promise((resolve) => {
      this.httpClient.post<boolean>(`${this.apiURL_Freelancer}/edit/${freelancer.id}`, freelancer, this.securityService.getAuthHeader()).subscribe({
        next: (result: boolean) => {
          resolve(result);
        },
        error: (error) => {
          console.error(error);
        }
      });
    });
  }

  /* SKILL */

  getAllSkills(): Observable<Skill[]> {
    return this.httpClient.get<Skill[]>(this.apiURL_Skill, this.securityService.getAuthHeader())
      .pipe(
        catchError(this.errorHandler)
      );
  }

  getAllFreelancerSkill(userId: number): Observable<FreelancerSkill[]> {
    return this.httpClient.get<FreelancerSkill[]>(`${this.apiURL_Skill}/${userId}`, this.securityService.getAuthHeader())
    .pipe(
      catchError(this.errorHandler)
    )
  }

  addSkillToFreelancer(userId: number, skillId: number): Observable<boolean> {
    return this.httpClient.get<boolean>(`${this.apiURL_Skill}/add/${userId}/${skillId}`, this.securityService.getAuthHeader())
    .pipe(
      catchError(this.errorHandler)
    )
  }

  removeSkillOfFreelancer(userId: number, skillId: number): Observable<boolean> {
    return this.httpClient.get<boolean>(`${this.apiURL_Skill}/remove/${userId}/${skillId}`, this.securityService.getAuthHeader())
    .pipe(
      catchError(this.errorHandler)
    )
  }

  changeOutstandingSkillOfFreelancer(userId: number, skillId: number): Observable<boolean> {
    return this.httpClient.get<boolean>(`${this.apiURL_Skill}/change-outstanding/${userId}/${skillId}`, this.securityService.getAuthHeader())
    .pipe(
      catchError(this.errorHandler)
    )
  }

  /* LOCATION */

  getAllLocation(): Observable<Location[]> {
    return this.httpClient.get<Location[]>(`${this.apiURL_Location}`, this.securityService.getAuthHeader())
    .pipe(
      catchError(this.errorHandler)
    )
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
