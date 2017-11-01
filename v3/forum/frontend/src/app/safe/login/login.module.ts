import { NgModule } from '@angular/core';

import { SharedModule } from '../../shared';
import { LoginComponent } from './login.component';
import { LoginRoutingModule } from './login-routing.module';
import { LoginService } from './login.service';

@NgModule({
  imports: [
    SharedModule, LoginRoutingModule
  ],
  declarations: [
    LoginComponent
  ],
  providers: [
    LoginService
  ]

})
export class LoginModule { }
