import { NgModule } from '@angular/core';

import { ConfirmationPopoverModule } from 'angular-confirmation-popover';
import { CepRoutingModule } from './cep-routing.module';
import { SharedModule } from '../shared';

import { CepService } from './cep.service';
import { CepComponent } from './cep.component';

@NgModule({
    imports: [
        SharedModule,
        CepRoutingModule,
        ConfirmationPopoverModule.forRoot({
            confirmText: 'Sim',
            cancelText: 'Não',
            appendToBody: true
        })
    ],
    declarations: [
        CepComponent
    ],
    providers: [CepService],
    exports: []
})
export class CepModule { }
