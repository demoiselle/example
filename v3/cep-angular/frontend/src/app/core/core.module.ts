import {
  ModuleWithProviders,
  NgModule,
  Optional,
  SkipSelf
} from '@angular/core';
import { CommonModule } from '@angular/common';

import { AuthServiceProvider } from '@demoiselle/security';
import { HttpServiceProvider, ExceptionService } from '@demoiselle/http';

import { LoginService } from '../login/login.service';

@NgModule({
  imports: [CommonModule],
  declarations: [],
  exports: [],
  providers: []
})
export class CoreModule {

  static forRoot(): ModuleWithProviders {
    return {
      ngModule: CoreModule,
      providers: [
        // put here your global (singleton) services to be available for all components
        HttpServiceProvider({
          endpoints: process.env.CONF.endpoints,
          multitenancy: process.env.CONF.multitenancy,
          unAuthorizedRoute: '/login',
          tokenKey: 'id_token'
        }),
        AuthServiceProvider({
          authEndpointUrl: 'http://localhost:8080/api/', // may be in the form 'http://localhost:9090/app/api/v1/'
          loginResourcePath: 'auth',
          tokenKey: 'id_token',
          loginRoute: '/login'
        }),
        LoginService,
        ExceptionService
      ]
    };
  }

  constructor( @Optional() @SkipSelf() parentModule: CoreModule) {
    if (parentModule) {
      throw new Error('CoreModule is already loaded. Import it in the AppModule only');
    }
  }
}
