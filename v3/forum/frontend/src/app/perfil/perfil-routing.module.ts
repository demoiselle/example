import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AuthGuard } from '@demoiselle/security';
import { PerfilComponent } from './perfil.component';
import { PerfilEditComponent } from './perfil-edit.component';
import { PerfilResolver } from './perfil.resolver';

const routes: Routes = [
    {
        path: '',
        canActivate: [AuthGuard],
        component: PerfilComponent
    },
    {
        path: 'edit/:id',
        canActivate: [AuthGuard],
        component: PerfilEditComponent,
        resolve: {
            perfil: PerfilResolver
        }
    },
    {
        path: 'edit',
        component: PerfilEditComponent
    }
];

@NgModule({
    imports: [
        RouterModule.forChild(routes)
    ],
    exports: [RouterModule]
})
export class PerfilRoutingModule { }
