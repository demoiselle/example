import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AuthGuard } from '@demoiselle/security';
import { VersiculoComponent } from './versiculo.component';
import { VersiculoEditComponent } from './versiculo-edit.component';
import { VersiculoResolver } from './versiculo.resolver';

const routes: Routes = [{
        path: '',
        canActivate: [AuthGuard],
        component: VersiculoComponent
    }, {
        path: 'edit/:id',
        canActivate: [AuthGuard],
        component: VersiculoEditComponent,
        resolve: {
            versiculo: VersiculoResolver
        }
    }, {
        path: 'edit',
        component: VersiculoEditComponent
    }
];

@NgModule({
    imports: [
        RouterModule.forChild(routes)
    ],
    exports: [RouterModule]
})
export class VersiculoRoutingModule { }
