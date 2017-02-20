import { NgModule } from '@angular/core';

import { ConfirmationPopoverModule } from 'angular-confirmation-popover';
import { MensagemRoutingModule } from './mensagem-routing.module';
import { SharedModule } from '../shared';

import { MensagemService } from './mensagem.service';
import { MensagemComponent } from './mensagem.component';
import { MensagemEditComponent } from './mensagem-edit.component';

@NgModule({
    imports: [
        SharedModule,
        MensagemRoutingModule,
        ConfirmationPopoverModule.forRoot({
            confirmText: 'Sim',
            cancelText: 'Não',
            appendToBody: true
        })
    ],
    declarations: [
        MensagemComponent, MensagemEditComponent
    ],
    providers: [MensagemService],
    exports: []
})
export class MensagemModule { }
