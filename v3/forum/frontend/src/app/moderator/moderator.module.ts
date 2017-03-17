import { NgModule } from '@angular/core';

import { ConfirmationPopoverModule } from 'angular-confirmation-popover';
import { ModeratorRoutingModule } from './moderator-routing.module';
import { SharedModule } from '../shared';

import { ModeratorService } from './moderator.service';
import { ModeratorComponent } from './moderator.component';
import { ModeratorEditComponent } from './moderator-edit.component';

@NgModule({
    imports: [
        SharedModule,
        ModeratorRoutingModule,
        ConfirmationPopoverModule.forRoot({
            confirmText: 'Sim',
            cancelText: 'Não',
            appendToBody: true
        })
    ],
    declarations: [
        ModeratorComponent, ModeratorEditComponent
    ],
    providers: [ModeratorService],
    exports: []
})
export class ModeratorModule { }
