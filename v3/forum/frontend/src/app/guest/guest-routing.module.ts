import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AuthGuard } from '@demoiselle/security';
import { GuestComponent } from './guest.component';
import { GuestEditComponent } from './guest-edit.component';
import { GuestResolver } from './guest.resolver';

const routes: Routes = [
    {
        path: '',
        canActivate: [AuthGuard],
        component: GuestComponent
    },
    {
        path: 'edit/:id',
        canActivate: [AuthGuard],
        component: GuestEditComponent,
        resolve: {
            guest: GuestResolver
        }
    },
    {
        path: 'edit',
        component: GuestEditComponent
    }
];

@NgModule({
    imports: [
        RouterModule.forChild(routes)
    ],
    exports: [RouterModule]
})
export class GuestRoutingModule { }
