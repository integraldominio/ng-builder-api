import { Injectable } from '@angular/core';
import { Router, CanActivate } from '@angular/router';
import { AuthenticationService } from './authentication.service';

@Injectable()
export class GuestGuard implements CanActivate {

  constructor(
    private router: Router,
    private authenticationService: AuthenticationService) {}

  canActivate(): boolean {
    if (this.authenticationService.getCurrentUser()) {
      this.router.navigate(['/']);
      return false;
    } else {
      return true;
    }
  }
}
