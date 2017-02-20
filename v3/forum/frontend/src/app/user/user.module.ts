import { NgModule } from '@angular/core';

import { ConfirmationPopoverModule } from 'angular-confirmation-popover';
import { UserRoutingModule } from './user-routing.module';
import { SharedModule } from '../shared';

import { UserService } from './user.service';
import { UserComponent } from './user.component';
import { UserEditComponent } from './user-edit.component';

@NgModule({
    imports: [
        SharedModule,
        UserRoutingModule,
        ConfirmationPopoverModule.forRoot({
            confirmText: 'Sim',
            cancelText: 'Não',
            appendToBody: true
        })
    ],
    declarations: [
        UserComponent, UserEditComponent
    ],
    providers: [UserService],
    exports: []
})
export class UserModule { }
