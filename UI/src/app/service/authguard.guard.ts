import { RegistrationServiceService } from './registration-service.service';
import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Observable, of, from } from 'rxjs';

import { Router } from '@angular/router';

@Injectable()
export class AuthguardGuard implements CanActivate {
  userRole:string;
  constructor(private user: RegistrationServiceService, private router: Router) {

  }
  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    this.userRole = localStorage.getItem("Role");
    if (this.user.getUserLoggedIn()) {
      console.log('inside AuthGuard', this.user.getUserLoggedIn());
      return true;
    }
     this.router.navigate['/login'];
    }
  }
