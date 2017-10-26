import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AuthGuard } from '@demoiselle/security';
import { UserComponent } from './user.component';
import { UserEditComponent } from './user-edit.component';
import { UserResolver } from './user.resolver';

const routes: Routes = [
    {
        path: '',
        canActivate: [AuthGuard],
        component: UserComponent
    },
    {
        path: 'edit/:id',
        canActivate: [AuthGuard],
        component: UserEditComponent,
        resolve: {
            user: UserResolver
        }
    },
    {
        path: 'edit',
        component: UserEditComponent
    }
];

@NgModule({
    imports: [
        RouterModule.forChild(routes)
    ],
    exports: [RouterModule]
})
export class UserRoutingModule { }
