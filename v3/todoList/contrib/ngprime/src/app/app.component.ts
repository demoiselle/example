import { AfterContentInit, Component, ViewContainerRef } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '@demoiselle/security';

// global style
import '../style/global.scss';

@Component({
  selector: 'todo-app', // <todo-app></todo-app>
  styleUrls: ['./app.component.scss'],
  template: require('./app.component.html')
})
export class AppComponent implements AfterContentInit {
  private viewContainerRef: ViewContainerRef;

  public constructor(
    viewContainerRef: ViewContainerRef,
    // componentsHelper:ComponentsHelper,
    private router: Router,
    private authService: AuthService,
  ) {
    // You need this small hack in order to catch application root view container ref (ng2-bootstrap)
    this.viewContainerRef = viewContainerRef;
    
  }

  public ngAfterContentInit(): any {
    // uncomment if the application need to retoken
    // this.authService.initializeReTokenPolling();
  }
}
