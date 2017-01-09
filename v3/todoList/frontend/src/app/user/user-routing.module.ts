import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AuthGuard } from '@demoiselle/security';
import { UserComponent } from './user.component';
import { UserEditComponent } from './user-edit/user-edit.component';

@NgModule({
    imports: [
        RouterModule.forChild([
            {
                path: 'user',
                data: ['Usuário'],
                // canActivate: [AuthGuard],
                component: UserComponent
            },

            // edição de usuário
            {
                path: 'user/edit/:id',
                // canActivate: [AuthGuard],
                component: UserEditComponent
            },

            // novo usuário
            {
                path: 'user/edit',
                canActivate: [AuthGuard],
                component: UserEditComponent
            }
        ])
    ],
    exports: [RouterModule]
})
export class UserRoutingModule { }
