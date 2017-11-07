import {
  ModuleWithProviders,
  NgModule,
  Optional,
  SkipSelf
} from '@angular/core';

import { CommonModule } from '@angular/common';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpModule, BrowserXhr } from '@angular/http';
import { RouterModule } from '@angular/router';
import { Http, RequestOptions, ConnectionBackend, RequestOptionsArgs, Response, XHRBackend } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { Router } from '@angular/router';
import { LocationStrategy, HashLocationStrategy } from '@angular/common';

import { AuthServiceProvider, AuthService, SecurityModule } from '@demoiselle/security';
import { HttpServiceProvider, ExceptionService, HttpService } from '@demoiselle/http';
import { LoginService } from '../auth/login/login.service';

// Import 3rd party components
import { ToastModule, ToastOptions } from 'ng2-toastr/ng2-toastr';
import { BsDropdownModule } from 'ngx-bootstrap/dropdown';
import { NgProgressModule, NgProgressBrowserXhr } from 'ngx-progressbar';

// BEGIN Demoiselle Http and Security configs and factories
const httpConfig = {
  endpoints: {
    main: 'https://util.pgxp.com.br/dados/api/v1/'
  },
  multitenancy: null,
  unAuthorizedRoute: '/login',
  tokenKey: 'id_token'
};
const authConfig = {
  authEndpointUrl: 'https://util.pgxp.com.br/dados/api/', // may be in the form 'http://localhost:9090/app/api/v1/'
  loginResourcePath: 'auth',
  tokenKey: 'id_token',
  loginRoute: '/login'
};

export function httpFactory(backend: XHRBackend, defaultOptions: RequestOptions, router: Router, exceptionService: ExceptionService) {
  return new HttpService(backend, defaultOptions, router, exceptionService, httpConfig);
}

export function authFactory(http: Http, router: Router) {
  return new AuthService(http, router, authConfig);
}
// END Demoiselle Http and Security configs and factories


// Toastr Custom configs (defined at forRoot() providers below)
export class CustomOption extends ToastOptions {
  animate = 'flyRight'; // you can override any options available
  positionClass = 'toast-bottom-right';
  showCloseButton = true;
  dismiss = 'auto';
  toastLife = 7000;
}

// Import containers
import {
  FullLayout,
  SimpleLayout
} from '../shared/layout/containers';

const APP_CONTAINERS = [
  FullLayout,
  SimpleLayout
]

// Import components
import {
  AppAside,
  AppBreadcrumbs,
  AppFooter,
  AppHeader,
  AppSidebar,
  AppSidebarFooter,
  AppSidebarForm,
  AppSidebarHeader,
  AppSidebarMinimizer
} from '../shared/layout/components';

const APP_COMPONENTS = [
  AppAside,
  AppBreadcrumbs,
  AppFooter,
  AppHeader,
  AppSidebar,
  AppSidebarFooter,
  AppSidebarForm,
  AppSidebarHeader,
  AppSidebarMinimizer
]

// Import directives
import {
  AsideToggleDirective,
  NAV_DROPDOWN_DIRECTIVES,
  SIDEBAR_TOGGLE_DIRECTIVES
} from '../shared/layout/directives';

const APP_DIRECTIVES = [
  AsideToggleDirective,
  NAV_DROPDOWN_DIRECTIVES,
  SIDEBAR_TOGGLE_DIRECTIVES
]

@NgModule({
  imports: [
    //CommonModule,
    NgProgressModule,
    BrowserAnimationsModule,
    BrowserModule,
    HttpModule,
    RouterModule,
    SecurityModule,
    BsDropdownModule.forRoot(),
    ToastModule.forRoot()

  ],
  declarations: [
    ...APP_CONTAINERS,
    ...APP_COMPONENTS,
    ...APP_DIRECTIVES
  ],
  exports: [],
  providers: []
})
export class CoreModule {

  static forRoot(): ModuleWithProviders {
    return {
      ngModule: CoreModule,
      providers: [
        // put here your global or singleton services to be available for all modules
        {
          provide: Http,
          useFactory: httpFactory,
          deps: [XHRBackend, RequestOptions, Router, ExceptionService]
        },
        {
          provide: AuthService,
          useFactory: authFactory,
          deps: [Http, Router]
        },
        LoginService,
        ExceptionService,
        { provide: ToastOptions, useClass: CustomOption },
        { provide: LocationStrategy, useClass: HashLocationStrategy },
        { provide: BrowserXhr, useClass: NgProgressBrowserXhr }
      ]
    };
  }

  constructor( @Optional() @SkipSelf() parentModule: CoreModule) {
    if (parentModule) {
      throw new Error('CoreModule is already loaded. Import it in the AppModule only');
    }
  }
}
