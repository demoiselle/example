import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AuthGuard } from '@demoiselle/security';
import { CategoriaComponent } from './categoria.component';
import { CategoriaEditComponent } from './categoria-edit.component';

@NgModule({
    imports: [
        RouterModule.forChild([
            {
                path: 'categoria',
                data: ['Categoria'],
                // canActivate: [AuthGuard],
                component: CategoriaComponent
            },
            {
                path: 'categoria/edit/:id',
                // canActivate: [AuthGuard],
                component: CategoriaEditComponent
            },
            {
                path: 'categoria/edit',
                // canActivate: [AuthGuard],
                component: CategoriaEditComponent
            }
        ])
    ],
    exports: [RouterModule]
})
export class CategoriaRoutingModule { }
