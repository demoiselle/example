import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AuthGuard } from '@demoiselle/security';
import { MensagemComponent } from './mensagem.component';
import { MensagemEditComponent } from './mensagem-edit.component';

@NgModule({
    imports: [
        RouterModule.forChild([
            {
                path: 'mensagem',
                data: ['Mensagem'],
                // canActivate: [AuthGuard],
                component: MensagemComponent
            },
            {
                path: 'mensagem/edit/:id',
                // canActivate: [AuthGuard],
                component: MensagemEditComponent
            },
            {
                path: 'mensagem/edit',
                // canActivate: [AuthGuard],
                component: MensagemEditComponent
            }
        ])
    ],
    exports: [RouterModule]
})
export class MensagemRoutingModule { }
