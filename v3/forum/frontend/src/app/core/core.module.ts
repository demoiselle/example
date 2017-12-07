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
import { HttpClientModule, HTTP_INTERCEPTORS, HttpClient } from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { Http, RequestOptions, ConnectionBackend, RequestOptionsArgs, Response, XHRBackend } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { Router } from '@angular/router';
import { LocationStrategy, HashLocationStrategy } from '@angular/common';

import { ServiceWorkerService } from './sw.service';
import { NgServiceWorker } from '@angular/service-worker';
import { CredentialManagementService } from '../auth/credentials.service';
import { WebSocketService } from './websocket.service';
import { NotificationService } from './notification.service';

import { AuthService, SecurityModule, TokenService } from '@demoiselle/security';
import { ExceptionService, AuthInterceptor, DmlHttpModule } from '@demoiselle/http';
import { AuthOptions } from '@demoiselle/security/dist/auth-options';

// Import 3rd party components
import { ToastModule, ToastOptions } from 'ng2-toastr/ng2-toastr';
import { BsDropdownModule } from 'ngx-bootstrap/dropdown';
import { NgProgressModule, NgProgressBrowserXhr } from 'ngx-progressbar';

import { environment } from '../../environments/environment';

import { SocialLoginModule, AuthServiceConfig, GoogleLoginProvider, FacebookLoginProvider } from "angular5-social-login";

export function getAuthServiceConfigs() {
  let config = new AuthServiceConfig(
    [
      {
        id: FacebookLoginProvider.PROVIDER_ID,
        provider: new FacebookLoginProvider(environment.facebookId)
      },
      {
        id: GoogleLoginProvider.PROVIDER_ID,
        provider: new GoogleLoginProvider(environment.googleId)
      },
    ]
  );
  return config;
}

// Demoiselle AuthOptions, using default values except api endpoint
export class MyAuthOptions extends AuthOptions {
  authEndpointUrl = environment.apiUrl;
}

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
    NgProgressModule,
    SocialLoginModule,
    BrowserAnimationsModule,
    BrowserModule,
    // HttpModule,
    HttpClientModule,
    RouterModule,
    SecurityModule.forRoot(),
    BsDropdownModule.forRoot(),
    ToastModule.forRoot()

  ],
  declarations: [
    ...APP_CONTAINERS,
    ...APP_COMPONENTS,
    ...APP_DIRECTIVES
  ],
  exports: [],
  providers: [{
    provide: AuthServiceConfig,
    useFactory: getAuthServiceConfigs
  }]
})
export class CoreModule {

  static forRoot(): ModuleWithProviders {
    return {
      ngModule: CoreModule,
      providers: [
        // put here your global or singleton services to be available for all modules
        {
          provide: HTTP_INTERCEPTORS,
          useClass: AuthInterceptor,
          multi: true,
        },
        {
          provide: AuthOptions,
          useClass: MyAuthOptions
        },
        ExceptionService,
        ServiceWorkerService,
        NgServiceWorker,
        CredentialManagementService,
        WebSocketService,
        NotificationService,
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
