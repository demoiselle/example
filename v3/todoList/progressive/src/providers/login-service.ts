import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Http } from '@angular/http';

import { AuthService } from '@demoiselle/security';

@Injectable()
export class LoginService {

    protected redirectTo: any = null;

    constructor(protected router: Router, protected http: Http, protected authService: AuthService) { }

    register(user: any) {
        let url = 'http://todolist-demoiselle.44fs.preview.openshiftapps.com/api/user/';
        return this.http.post(url, {
            firstName: user.name,
            email: user.email,
            pass: user.pass,
        });
    }

    signin(credentials: any) {
        return this.authService.login(credentials);
    }

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
