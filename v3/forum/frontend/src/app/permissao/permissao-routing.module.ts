import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AuthGuard } from '@demoiselle/security';
import { PermissaoComponent } from './permissao.component';
import { PermissaoEditComponent } from './permissao-edit.component';
import { PermissaoResolver } from './permissao.resolver';

const routes: Routes = [
    {
        path: '',
        canActivate: [AuthGuard],
        component: PermissaoComponent
    },
    {
        path: 'edit/:id',
        canActivate: [AuthGuard],
        component: PermissaoEditComponent,
        resolve: {
            permissao: PermissaoResolver
        }
    },
    {
        path: 'edit',
        component: PermissaoEditComponent
    }
];

@NgModule({
    imports: [
        RouterModule.forChild(routes)
    ],
    exports: [RouterModule]
})
export class PermissaoRoutingModule { }
