import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

// import { AuthGuard } from '@demoiselle/security';
import { TodoComponent } from './todo.component';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'todo',
        data: ['ToDo'],
        // canActivate: [AuthGuard],
        component: TodoComponent
      }
    ])
  ],
  exports: [RouterModule]
})
export class TodoRoutingModule { }
