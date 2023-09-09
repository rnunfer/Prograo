import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Chat } from 'src/app/interfaces/Chat';
import { FreelancerSkill } from 'src/app/interfaces/FreelancerSkill';
import { Project } from 'src/app/interfaces/Project';
import { SecurityService } from 'src/app/security/security.service';

@Injectable({
  providedIn: 'root'
})
export class UserProjectService {

  private apiURL_Project = "http://localhost:8080/api/v1/projects";
  private apiURL_Calification = "http://localhost:8080/api/v1/califications";
  private apiURL_Chats = "http://localhost:8080/api/v1/chats";
  private apiURL_Skills = "http://localhost:8080/api/v1/skills";

  constructor(private httpClient: HttpClient, private securityService: SecurityService) { }

  getAllProjects(): Promise<Project[]> {
    return new Promise((resolve) => {
      let userId = this.securityService.getUserId();
      this.httpClient.get<Project[]>(`${this.apiURL_Project}/by/${userId}`, this.securityService.getAuthHeader()).subscribe({
        next: (result: Project[]) => {
          resolve(result);
        },
        error: (error) => {
          console.error(error);
        }
      })
    })  
  }

  getProjectById(projectId: number): Promise<Project> {
    return new Promise((resolve) => {
      this.httpClient.get<Project>(`${this.apiURL_Project}/${projectId}`, this.securityService.getAuthHeader()).subscribe({
        next: (result: Project) => {
          resolve(result);
        },
        error: (error) => {
          console.error(error);
        }
      })
    })  
  }

  editProject(project: Project): Promise<boolean> {
    return new Promise((resolve) => {
      this.httpClient.post<boolean>(`${this.apiURL_Project}/edit`, project, this.securityService.getAuthHeader()).subscribe({
        next: (result: boolean) => {
          resolve(result);
        },
        error: (error) => {
          console.error(error);
        }
      })
    })
  }

  signProjectBySeeker(projectId: number): Promise<boolean> {
    return new Promise((resolve) => {
      let userId: number = this.securityService.getUserId();
      this.httpClient.get<boolean>(`${this.apiURL_Project}/${projectId}/by-seeker/${userId}`, this.securityService.getAuthHeader()).subscribe({
        next: (result: boolean) => {
          resolve(result);
        },
        error: (error) => {
          console.error(error);
        }
      })
    })
  }

  signProjectByFreelancer(projectId: number): Promise<boolean> {
    return new Promise((resolve) => {
      let userId: number = this.securityService.getUserId();
      this.httpClient.get<boolean>(`${this.apiURL_Project}/${projectId}/by-freelancer/${userId}`, this.securityService.getAuthHeader()).subscribe({
        next: (result: boolean) => {
          resolve(result);
        },
        error: (error) => {
          console.error(error);
        }
      })
    })
  }

  sendCalification(projectId: number, content: string, note: number) {
    let data = {content, note};
    this.httpClient.post(`${this.apiURL_Calification}/set-calification/${projectId}`, data, this.securityService.getAuthHeader()).subscribe();
  }

  getAllChatsFromProject(projectId: number): Promise<Chat[]> {
    return new Promise((resolve) => {
      this.httpClient.get<Chat[]>(`${this.apiURL_Chats}/chats-from-project/${projectId}`, this.securityService.getAuthHeader()).subscribe({
        next: (result: Chat[]) => {
          resolve(result);
        },
        error: (error) => {
          console.error(error);
        }
      })
    })
  }

  sendMessageToProject(projectId: number, message: string): Promise<boolean> {
    return new Promise((resolve) => {
      let userId: number = this.securityService.getUserId();
      this.httpClient.post<boolean>(`${this.apiURL_Chats}/send-message-to/${projectId}/by/${userId}`, message, this.securityService.getAuthHeader()).subscribe({
        next: (result: boolean) => {
          resolve(result);
        },
        error: (error) => {
          console.error(error);
        }
      })
    })
  }

  getAllFreelancerSkill(userFreelancerId: number): Promise<FreelancerSkill[]> {
    return new Promise((resolve) => {
      this.httpClient.get<FreelancerSkill[]>(`${this.apiURL_Skills}/${userFreelancerId}`, this.securityService.getAuthHeader()).subscribe({
        next: (result: FreelancerSkill[]) => {
          resolve(result);
        },
        error: (error) => {
          console.error(error);
        }
      })
    })
  }
  
  addSkillToProject(skillId: number, projectId: number): Promise<boolean> {
    return new Promise((resolve) => {
      this.httpClient.get<boolean>(`${this.apiURL_Skills}/add-skill/${skillId}/to/${projectId}`, this.securityService.getAuthHeader()).subscribe({
        next: (result: boolean) => {
          resolve(result);
        },
        error: (error) => {
          console.error(error);
        }
      })
    })
  }

  removeSkillFromProject(skillId: number, projectId: number): Promise<boolean> {
    return new Promise((resolve) => {
      this.httpClient.get<boolean>(`${this.apiURL_Skills}/remove-skill/${skillId}/from/${projectId}`, this.securityService.getAuthHeader()).subscribe({
        next: (result: boolean) => {
          resolve(result);
        },
        error: (error) => {
          console.error(error);
        }
      })
    })
  }

}
