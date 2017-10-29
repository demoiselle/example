import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AuthGuard } from '@demoiselle/security';
import { ModeratorComponent } from './moderator.component';
import { ModeratorEditComponent } from './moderator-edit.component';
import { ModeratorResolver } from './moderator.resolver';

const routes: Routes = [
    {
        path: '',
        canActivate: [AuthGuard],
        component: ModeratorComponent
    },
    {
        path: 'edit/:id',
        canActivate: [AuthGuard],
        component: ModeratorEditComponent,
        resolve: {
            moderator: ModeratorResolver
        }
    },
    {
        path: 'edit',
        component: ModeratorEditComponent
    }
];

@NgModule({
    imports: [
        RouterModule.forChild(routes)
    ],
    exports: [RouterModule]
})
export class ModeratorRoutingModule { }
