import { NgModule } from '@angular/core';

import { ConfirmationPopoverModule } from 'angular-confirmation-popover';
import { TopicoRoutingModule } from './topico-routing.module';
import { SharedModule } from '../shared';

import { TopicoService } from './topico.service';
import { TopicoComponent } from './topico.component';
import { TopicoEditComponent } from './topico-edit.component';

@NgModule({
    imports: [
        SharedModule,
        TopicoRoutingModule,
        ConfirmationPopoverModule.forRoot({
            confirmText: 'Sim',
            cancelText: 'Não',
            appendToBody: true
        })
    ],
    declarations: [
        TopicoComponent, TopicoEditComponent
    ],
    providers: [TopicoService],
    exports: []
})
export class TopicoModule { }
