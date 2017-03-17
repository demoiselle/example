import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AuthGuard } from '@demoiselle/security';
import { GuestComponent } from './guest.component';
import { GuestEditComponent } from './guest-edit.component';

@NgModule({
    imports: [
        RouterModule.forChild([
            {
                path: 'guest',
                data: ['Guest'],
                // canActivate: [AuthGuard],
                component: GuestComponent
            },
            {
                path: 'guest/edit/:id',
                // canActivate: [AuthGuard],
                component: GuestEditComponent
            },
            {
                path: 'guest/edit',
                // canActivate: [AuthGuard],
                component: GuestEditComponent
            }
        ])
    ],
    exports: [RouterModule]
})
export class GuestRoutingModule { }
