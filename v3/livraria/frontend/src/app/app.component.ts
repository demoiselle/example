import { AfterContentInit, Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '@demoiselle/security';
import { ToastCommunicationService } from './shared/toast-communication.service';

// global style
import '../style/global.scss';

@Component({
  selector: 'my-app', // <my-app></my-app>
  styleUrls: ['./app.component.scss'],
  template: require('./app.component.html')
})
export class AppComponent implements AfterContentInit {
  
  private toastyComponentPosition: string;
  
  public constructor(
    private router: Router,
    private authService: AuthService,
    private toastCommunicationService: ToastCommunicationService) {
      this.toastCommunicationService.position$.subscribe(pos => this.toastyComponentPosition = pos);
  }

  public ngAfterContentInit(): any {
    // uncomment if the application need to retoken
    // this.authService.initializeReTokenPolling();
  }
}
