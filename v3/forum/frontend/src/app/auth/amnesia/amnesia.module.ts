import { NgModule } from '@angular/core';

import { SharedModule } from '../../shared';
import { AmnesiaComponent } from './amnesia.component';
import { AmnesiaRoutingModule } from './amnesia-routing.module';


@NgModule({
  imports: [
    SharedModule, AmnesiaRoutingModule
  ],
  declarations: [
    AmnesiaComponent
  ],
  providers: []
})
export class AmnesiaModule { }
