import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AuthGuard } from '@demoiselle/security';
import { ModeratorComponent } from './moderator.component';
import { ModeratorEditComponent } from './moderator-edit.component';

@NgModule({
    imports: [
        RouterModule.forChild([
            {
                path: 'moderator',
                data: ['Moderator'],
                // canActivate: [AuthGuard],
                component: ModeratorComponent
            },
            {
                path: 'moderator/edit/:id',
                // canActivate: [AuthGuard],
                component: ModeratorEditComponent
            },
            {
                path: 'moderator/edit',
                // canActivate: [AuthGuard],
                component: ModeratorEditComponent
            }
        ])
    ],
    exports: [RouterModule]
})
export class ModeratorRoutingModule { }
