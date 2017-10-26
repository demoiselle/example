import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AuthGuard } from '@demoiselle/security';
import { MensagemComponent } from './mensagem.component';
import { MensagemEditComponent } from './mensagem-edit.component';
import { MensagemResolver } from './mensagem.resolver';

const routes: Routes = [
    {
        path: '',
        canActivate: [AuthGuard],
        component: MensagemComponent
    },
    {
        path: 'edit/:id',
        canActivate: [AuthGuard],
        component: MensagemEditComponent,
        resolve: {
            mensagem: MensagemResolver
        }
    },
    {
        path: 'edit',
        component: MensagemEditComponent
    }
];

@NgModule({
    imports: [
        RouterModule.forChild(routes)
    ],
    exports: [RouterModule]
})
export class MensagemRoutingModule { }
