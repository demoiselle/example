import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AuthGuard } from '@demoiselle/security';
import { TopicoComponent } from './topico.component';
import { TopicoEditComponent } from './topico-edit.component';

@NgModule({
    imports: [
        RouterModule.forChild([
            {
                path: 'topico',
                data: ['Topico'],
                // canActivate: [AuthGuard],
                component: TopicoComponent
            },
            {
                path: 'topico/edit/:id',
                // canActivate: [AuthGuard],
                component: TopicoEditComponent
            },
            {
                path: 'topico/edit',
                // canActivate: [AuthGuard],
                component: TopicoEditComponent
            }
        ])
    ],
    exports: [RouterModule]
})
export class TopicoRoutingModule { }
