import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

import { SecurityModule } from '@demoiselle/security';

import { NotificationService } from './notification.service';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    RouterModule,
    SecurityModule
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
    SecurityModule
  ]
})

export class SharedModule { }
