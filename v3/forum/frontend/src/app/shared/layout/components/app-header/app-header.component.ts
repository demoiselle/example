import { Component, ElementRef } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '@demoiselle/security';

@Component({
  selector: 'app-header',
  templateUrl: './app-header.component.html'
})
export class AppHeader {

  constructor(private el: ElementRef, private router: Router, private authService: AuthService) { }

  //wait for the component to render completely
  ngOnInit(): void {
    var nativeElement: HTMLElement = this.el.nativeElement,
    parentElement: HTMLElement = nativeElement.parentElement;
    // move all children out of the element
    while (nativeElement.firstChild) {
      parentElement.insertBefore(nativeElement.firstChild, nativeElement);
    }
    // remove the empty element(the host)
    parentElement.removeChild(nativeElement);
  }

  isLoggedIn() {
    return this.authService.isAuthenticated();
  }

  logout() {
    this.authService.logout();
    this.router.navigateByUrl('/login');
  }
}
