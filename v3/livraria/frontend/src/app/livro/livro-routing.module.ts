import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AuthGuard } from '@demoiselle/security';
import { LivroComponent } from './livro.component';
import { LivroEditComponent } from './livro-edit.component';

@NgModule({
    imports: [
        RouterModule.forChild([
            {
                path: 'livro',
                data: ['Livro'],
                // canActivate: [AuthGuard],
                component: LivroComponent
            },
            {
                path: 'livro/edit/:id',
                // canActivate: [AuthGuard],
                component: LivroEditComponent
            },
            {
                path: 'livro/edit',
                // canActivate: [AuthGuard],
                component: LivroEditComponent
            }
        ])
    ],
    exports: [RouterModule]
})
export class LivroRoutingModule { }
