import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AuthGuard } from '@demoiselle/security';
import { TopicoComponent } from './topico.component';
import { TopicoEditComponent } from './topico-edit.component';
import { TopicoResolver } from './topico.resolver';

const routes: Routes = [{
        path: '',
        canActivate: [AuthGuard],
        component: TopicoComponent
    }, {
        path: 'edit/:id',
        canActivate: [AuthGuard],
        component: TopicoEditComponent,
        resolve: {
            topico: TopicoResolver
        }
    }, {
        path: 'edit',
        component: TopicoEditComponent
    }
];

@NgModule({
    imports: [
        RouterModule.forChild(routes)
    ],
    exports: [RouterModule]
})
export class TopicoRoutingModule { }
