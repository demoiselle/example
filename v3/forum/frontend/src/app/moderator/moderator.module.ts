import { NgModule } from '@angular/core';

import { ModeratorRoutingModule } from './moderator-routing.module';
import { SharedModule } from '../shared';

import { ModeratorService } from './moderator.service';
import { ModeratorComponent } from './moderator.component';
import { ModeratorEditComponent } from './moderator-edit.component';
import { ModeratorResolver } from './moderator.resolver';

@NgModule({
    imports: [
        SharedModule,
        ModeratorRoutingModule
    ],
    declarations: [
        ModeratorComponent,
        ModeratorEditComponent
    ],
    providers: [
        ModeratorService,
        ModeratorResolver
    ],
    exports: []
})
export class ModeratorModule { }
