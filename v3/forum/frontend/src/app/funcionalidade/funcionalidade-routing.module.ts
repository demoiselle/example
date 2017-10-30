import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AuthGuard } from '@demoiselle/security';
import { FuncionalidadeComponent } from './funcionalidade.component';
import { FuncionalidadeEditComponent } from './funcionalidade-edit.component';
import { FuncionalidadeResolver } from './funcionalidade.resolver';

const routes: Routes = [
    {
        path: '',
        canActivate: [AuthGuard],
        component: FuncionalidadeComponent
    },
    {
        path: 'edit/:id',
        canActivate: [AuthGuard],
        component: FuncionalidadeEditComponent,
        resolve: {
            funcionalidade: FuncionalidadeResolver
        }
    },
    {
        path: 'edit',
        component: FuncionalidadeEditComponent
    }
];

@NgModule({
    imports: [
        RouterModule.forChild(routes)
    ],
    exports: [RouterModule]
})
export class FuncionalidadeRoutingModule { }
