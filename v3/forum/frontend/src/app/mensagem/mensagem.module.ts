import { NgModule } from '@angular/core';

import { MensagemRoutingModule } from './mensagem-routing.module';
import { SharedModule } from '../shared';

import { MensagemService } from './mensagem.service';
import { MensagemComponent } from './mensagem.component';
import { MensagemEditComponent } from './mensagem-edit.component';
import { MensagemResolver } from './mensagem.resolver';
import { PaginationModule } from 'ngx-bootstrap';

@NgModule({
    imports: [
        SharedModule,
        MensagemRoutingModule,
        PaginationModule.forRoot()
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
