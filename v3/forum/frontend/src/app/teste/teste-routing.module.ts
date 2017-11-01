import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AuthGuard } from '@demoiselle/security';
import { TesteComponent } from './teste.component';
import { TesteEditComponent } from './teste-edit.component';
import { TesteResolver } from './teste.resolver';

const routes: Routes = [
    {
        path: '',
        canActivate: [AuthGuard],
        component: TesteComponent
    },
    {
        path: 'edit/:id',
        canActivate: [AuthGuard],
        component: TesteEditComponent,
        resolve: {
            teste: TesteResolver
        }
    },
    {
        path: 'edit',
        component: TesteEditComponent
    }
];

@NgModule({
    imports: [
        RouterModule.forChild(routes)
    ],
    exports: [RouterModule]
})
export class TesteRoutingModule { }
