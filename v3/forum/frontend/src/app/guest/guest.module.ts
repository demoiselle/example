import { NgModule } from '@angular/core';

import { GuestRoutingModule } from './guest-routing.module';
import { SharedModule } from '../shared';

import { GuestService } from './guest.service';
import { GuestComponent } from './guest.component';
import { GuestEditComponent } from './guest-edit.component';
import { GuestResolver } from './guest.resolver';

@NgModule({
    imports: [
        SharedModule,
        GuestRoutingModule
    ],
    declarations: [
        GuestComponent,
        GuestEditComponent
    ],
    providers: [
        GuestService,
        GuestResolver
    ],
    exports: []
})
export class GuestModule { }
