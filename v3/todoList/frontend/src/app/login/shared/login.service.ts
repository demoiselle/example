import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

import { AuthService } from '@demoiselle/security';

@Injectable()
export class LoginService {

  protected redirectTo: any = null;

  constructor(protected router: Router, protected authService: AuthService) { }

  loginIfNot(redirectTo: any) {
    if (this.authService.isAuthenticated()) {
      this.redirectTo = null;
      this.router.navigate(redirectTo);
    } else {
      this.redirectTo = redirectTo;
      this.router.navigate(['/login']);
    }
  }

  proceedToRedirect(path) {
    console.log('Redirect to:', this.redirectTo);

    if (this.redirectTo) {
      this.router.navigate(this.redirectTo);
    } else {
      this.router.navigate(path);
    }
  }

  setRedirect(path) {
    this.redirectTo = ['/' + path];
  }
}
