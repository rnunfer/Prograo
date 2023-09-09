import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { SecurityService } from './security.service';

@Injectable({
  providedIn: 'root'
})
export class UserTypeGuard implements CanActivate {

  constructor(private router: Router, private security: SecurityService) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | UrlTree {
    const userType = this.security.getUserTypeUser();
    const allowedRoutes = ['user'];
    if (allowedRoutes.includes(route.url[0].path) && (userType === 'seeker' || userType === 'freelancer' || userType === 'administrator')) {
      return true;
    } else {
      return this.router.parseUrl('/index');
    }
  }
}