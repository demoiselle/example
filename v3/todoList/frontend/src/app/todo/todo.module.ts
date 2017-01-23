import { NgModule } from '@angular/core';

import { ConfirmationPopoverModule } from 'angular-confirmation-popover';
import { SharedModule } from '../shared';

import { TodoRoutingModule } from './todo-routing.module';
import { TodoService } from './shared/todo.service';
import { TodoComponent } from './todo.component';


@NgModule({
  imports: [
    SharedModule,
    TodoRoutingModule,
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
