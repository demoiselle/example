import { AfterContentInit, Component, ViewContainerRef } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '@demoiselle/security';
import { ToastsManager } from 'ng2-toastr/ng2-toastr';

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
    public toastr: ToastsManager) {
    // You need this small hack in order to catch application root view container ref (ng2-bootstrap)
    this.viewContainerRef = viewContainerRef;
    // componentsHelper.setRootViewContainerRef(viewContainerRef);
    this.toastr.setRootViewContainerRef(viewContainerRef); // hack for angular >= 2.2
  }

  public ngAfterContentInit(): any {
    // uncomment if the application need to retoken
    // this.authService.initializeReTokenPolling();
  }
}
