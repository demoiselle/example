import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

import { Ng2BootstrapModule, PopoverModule } from 'ngx-bootstrap';
import { SecurityModule } from '@demoiselle/security';

import { NotificationService } from './notification.service';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    RouterModule,
    SecurityModule,
    Ng2BootstrapModule.forRoot(),
    PopoverModule.forRoot()

  ],
  declarations: [ 
  ],
  providers: [
    NotificationService
  ],
  exports: [
    CommonModule,
    FormsModule,
    RouterModule,
    Ng2BootstrapModule,
    SecurityModule

  ]
})
export class SharedModule { }
