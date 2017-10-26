import { NgModule } from '@angular/core';

import { MensagemRoutingModule } from './mensagem-routing.module';
import { SharedModule } from '../shared';

import { MensagemService } from './mensagem.service';
import { MensagemComponent } from './mensagem.component';
import { MensagemEditComponent } from './mensagem-edit.component';
import { MensagemResolver } from './mensagem.resolver';

@NgModule({
    imports: [
        SharedModule,
        MensagemRoutingModule
    ],
    declarations: [
        MensagemComponent,
        MensagemEditComponent
    ],
    providers: [
        MensagemService,
        MensagemResolver
    ],
    exports: []
})
export class MensagemModule { }
