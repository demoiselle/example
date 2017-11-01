import { NgModule } from '@angular/core';

import { TesteRoutingModule } from './teste-routing.module';
import { SharedModule } from '../shared';

import { TesteService } from './teste.service';
import { TesteComponent } from './teste.component';
import { TesteEditComponent } from './teste-edit.component';
import { TesteResolver } from './teste.resolver';
import { PaginationModule } from 'ngx-bootstrap';

@NgModule({
    imports: [
        SharedModule,
        TesteRoutingModule,
        PaginationModule.forRoot()
    ],
    declarations: [
        TesteComponent,
        TesteEditComponent
    ],
    providers: [
        TesteService,
        TesteResolver
    ],
    exports: []
})
export class TesteModule { }
