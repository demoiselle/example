import { NgModule } from '@angular/core';

import { SharedModule } from '../../shared';
import { RegisterComponent } from './register.component';
import { RegisterRoutingModule } from './register-routing.module';

@NgModule({
  imports: [
    SharedModule, RegisterRoutingModule
  ],
  declarations: [
    RegisterComponent
  ],
  providers: []
})
export class RegisterModule { }
