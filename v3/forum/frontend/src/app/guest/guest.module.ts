import { NgModule } from '@angular/core';

import { ConfirmationPopoverModule } from 'angular-confirmation-popover';
import { GuestRoutingModule } from './guest-routing.module';
import { SharedModule } from '../shared';

import { GuestService } from './guest.service';
import { GuestComponent } from './guest.component';
import { GuestEditComponent } from './guest-edit.component';

@NgModule({
    imports: [
        SharedModule,
        GuestRoutingModule,
        ConfirmationPopoverModule.forRoot({
            confirmText: 'Sim',
            cancelText: 'Não',
            appendToBody: true
        })
    ],
    declarations: [
        GuestComponent, GuestEditComponent
    ],
    providers: [GuestService],
    exports: []
})
export class GuestModule { }
