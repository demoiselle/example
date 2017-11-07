import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

import { AuthService } from '@demoiselle/security';

@Injectable()
export class RegisterService {

  constructor(protected router: Router, protected authService: AuthService) { }

}
