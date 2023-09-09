import { Injectable, OnInit } from '@angular/core';
import { User } from '../interfaces/User';
import { userMenuItem } from '../shared/user-menu/userMenuItem';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { Subject, throwError } from 'rxjs';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class SecurityService {

  private apiURL_User = "http://localhost:8080/api/v1/users";
  private user: User;
  private userMenuData: userMenuItem[];
  private userSubject = new Subject<User>();
  event$ = this.userSubject.asObservable();

  constructor(
    private http: HttpClient,
    private router: Router
  ) {

    this.user = {
      id: 0,
      name: '',
      email: '',
      title: '',
      profilePhoto: '',
      status: '',
      verified: false,
      city: '',
      country: '',
      userType: 'guest'
    }

    this.userMenuData = [
      {
        name: "Configuración",
        route: "/user/configuration",
        permission: ["freelancer", "seeker", "administrator"]
      },
      {
        name: "Propuestas",
        route: "/user/proposals",
        permission: ["freelancer", "seeker", "administrator"]
      },
      {
        name: "Proyectos",
        route: "/user/projects",
        permission: ["freelancer", "seeker", "administrator"]
      }
    ];
  }

  getMenu(): userMenuItem[] {
    let userMenu: userMenuItem[] = [];

    this.userMenuData.forEach((data: userMenuItem) => {
      if (data.permission.includes(this.user.userType)) {
        let dataCopy = { ...data };
        userMenu.push(dataCopy);
      }
    });

    return userMenu;
  }

  getUserId(): number {
    return this.user.id;
  }

  getUserTypeUser(): string {
    return this.user.userType;
  }

  getUser(): User {
    return {...this.user};
  }

  getStatus(): string {
    return this.user.status;
  }

  getAuthHeader(): { headers: HttpHeaders } {
    const userId = this.getUserId();
    const headers = new HttpHeaders().set('userAuthId', userId.toString());
    return { headers };
  }

  login(email: string, password: string): Promise<boolean> {
    return new Promise((resolve) => {
      const loginData = { email, password };
      this.http.post<User>(`${this.apiURL_User}/login`, loginData, this.getAuthHeader())
        .subscribe({
            next: (user: User) => {
                if (user.id != null) {
                    this.user = user;
                    this.userSubject.next({...this.user});
                    resolve(true);
                } else {
                    resolve(false);
                }
            },
            error: () => {
                catchError(this.errorHandler);
            }
        });
    });
  }

  register(email: string, password: string, userType: string): Promise<boolean> {
    return new Promise((resolve) => {
      const registerData = { email, password, userType };
      this.http.post<User>(`${this.apiURL_User}/register`, registerData, this.getAuthHeader())
        .subscribe({
            next: (user: User) => {
                if (user.id != null) {
                    this.user = user;
                    this.userSubject.next({...this.user});
                    resolve(true);
                } else {
                    resolve(false);
                }
            },
            error: (error) => {
                console.error(error);
            }
        });
    });
  }

  updateUser() {
    this.http.get<User>(`${this.apiURL_User}/${this.user.id}`, this.getAuthHeader())
      .subscribe({
          next: (user: User) => {
              if (user.id != null) {
                  this.user = user;
                  this.userSubject.next({...this.user});
              }
          },
          error: (error) => {
              console.error(error);
          }
      });
  }

  closeSession() {
    this.user = {
      id: 0,
      name: '',
      email: '',
      title: '',
      profilePhoto: '',
      status: '',
      verified: false,
      city: '',
      country: '',
      userType: 'guest'
    }
    this.userSubject.next(this.user);
    this.router.navigate(['/']);
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
