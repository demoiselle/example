import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AuthGuard } from '@demoiselle/security';
import { LivrariaComponent } from './livraria.component';
import { LivrariaEditComponent } from './livraria-edit.component';

@NgModule({
    imports: [
        RouterModule.forChild([
            {
                path: 'livraria',
                data: ['Livraria'],
                // canActivate: [AuthGuard],
                component: LivrariaComponent
            },
            {
                path: 'livraria/edit/:id',
                // canActivate: [AuthGuard],
                component: LivrariaEditComponent
            },
            {
                path: 'livraria/edit',
                // canActivate: [AuthGuard],
                component: LivrariaEditComponent
            }
        ])
    ],
    exports: [RouterModule]
})
export class LivrariaRoutingModule { }
