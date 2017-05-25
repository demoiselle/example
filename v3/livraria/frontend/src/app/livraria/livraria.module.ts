import { NgModule } from '@angular/core';

import { ConfirmationPopoverModule } from 'angular-confirmation-popover';
import { LivrariaRoutingModule } from './livraria-routing.module';
import { SharedModule } from '../shared';

import { LivrariaService } from './livraria.service';
import { LivrariaComponent } from './livraria.component';
import { LivrariaEditComponent } from './livraria-edit.component';

@NgModule({
    imports: [
        SharedModule,
        LivrariaRoutingModule,
        ConfirmationPopoverModule.forRoot({
            confirmText: 'Sim',
            cancelText: 'Não',
            appendToBody: true
        })
    ],
    declarations: [
        LivrariaComponent, LivrariaEditComponent
    ],
    providers: [LivrariaService],
    exports: []
})
export class LivrariaModule { }
