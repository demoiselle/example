import { NgModule } from '@angular/core';

import { ConfirmationPopoverModule } from 'angular-confirmation-popover';
import { SharedModule } from '../shared';

import { TodoRoutingModule } from './todo-routing.module';
import { TodoService } from './shared/todo.service';
import { TodoComponent } from './todo.component';

import { DataTableModule, SharedModule as SharedModulePrime } from 'primeng/primeng';
import {DialogModule} from 'primeng/primeng';
import {InputTextModule} from 'primeng/primeng';
import {ButtonModule, CalendarModule} from 'primeng/primeng';



@NgModule({
  imports: [
    SharedModule,
    TodoRoutingModule,

    DataTableModule, SharedModulePrime, DialogModule, InputTextModule, ButtonModule, CalendarModule,

    ConfirmationPopoverModule.forRoot({
      confirmText: 'Sim',
      cancelText: 'Não',
      appendToBody: false
    })
  ],
  declarations: [
    TodoComponent
  ],
  providers: [TodoService],
  exports: []
})
export class TodoModule { }
