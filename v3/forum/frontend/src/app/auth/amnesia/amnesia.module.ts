import { NgModule } from '@angular/core';

import { SharedModule } from '../../shared';
import { AmnesiaComponent } from './amnesia.component';
import { AmnesiaRoutingModule } from './amnesia-routing.module';
import { AmnesiaService } from './amnesia.service';


@NgModule({
  imports: [
    SharedModule, AmnesiaRoutingModule
  ],
  declarations: [
    AmnesiaComponent
  ],
  providers: [
    AmnesiaService
  ]
})
export class AmnesiaModule { }
