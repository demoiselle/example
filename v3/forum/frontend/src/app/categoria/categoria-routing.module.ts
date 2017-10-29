import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AuthGuard } from '@demoiselle/security';
import { CategoriaComponent } from './categoria.component';
import { CategoriaEditComponent } from './categoria-edit.component';
import { CategoriaResolver } from './categoria.resolver';

const routes: Routes = [
    {
        path: '',
        canActivate: [AuthGuard],
        component: CategoriaComponent
    },
    {
        path: 'edit/:id',
        canActivate: [AuthGuard],
        component: CategoriaEditComponent,
        resolve: {
            categoria: CategoriaResolver
        }
    },
    {
        path: 'edit',
        component: CategoriaEditComponent
    }
];

@NgModule({
    imports: [
        RouterModule.forChild(routes)
    ],
    exports: [RouterModule]
})
export class CategoriaRoutingModule { }
