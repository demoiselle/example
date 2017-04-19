import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

import { AuthService } from '@demoiselle/security';

@Injectable()
export class LoginService {

  constructor(protected router: Router, protected authService: AuthService) { }

  login(user) {
    return this.authService.login(user);
  }
}
