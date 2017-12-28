import { NgModule } from '@angular/core';

import { VersiculoRoutingModule } from './versiculo-routing.module';
import { SharedModule } from '../shared';

import { VersiculoService } from './versiculo.service';
import { VersiculoComponent } from './versiculo.component';
import { VersiculoEditComponent } from './versiculo-edit.component';
import { VersiculoResolver } from './versiculo.resolver';
import { ModalModule, PaginationModule } from 'ngx-bootstrap';

@NgModule({
    imports: [
        SharedModule,
        VersiculoRoutingModule,
        PaginationModule.forRoot(),
        ModalModule.forRoot()
    ],
    declarations: [
        VersiculoComponent,
        VersiculoEditComponent
    ],
    providers: [
        VersiculoService,
        VersiculoResolver
    ],
    exports: []
})
export class VersiculoModule { }
