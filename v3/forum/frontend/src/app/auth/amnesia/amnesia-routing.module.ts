import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AmnesiaComponent } from './amnesia.component';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: '',
        component: AmnesiaComponent
      }
    ])
  ],
  exports: [RouterModule]
})
export class AmnesiaRoutingModule { }
