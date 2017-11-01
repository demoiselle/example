import { NgModule } from '@angular/core';

import { SharedModule } from '../../shared';
import { RegisterComponent } from './register.component';
import { RegisterRoutingModule } from './register-routing.module';
import { RegisterService } from './register.service';

@NgModule({
  imports: [
    SharedModule, RegisterRoutingModule
  ],
  declarations: [
    RegisterComponent
  ],
  providers: [
    RegisterService
  ]
})
export class RegisterModule { }
