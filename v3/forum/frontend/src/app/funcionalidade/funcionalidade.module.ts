import { NgModule } from '@angular/core';

import { FuncionalidadeRoutingModule } from './funcionalidade-routing.module';
import { SharedModule } from '../shared';

import { FuncionalidadeService } from './funcionalidade.service';
import { FuncionalidadeComponent } from './funcionalidade.component';
import { FuncionalidadeEditComponent } from './funcionalidade-edit.component';
import { FuncionalidadeResolver } from './funcionalidade.resolver';
import { ModalModule, PaginationModule } from 'ngx-bootstrap';

@NgModule({
    imports: [
        SharedModule,
        FuncionalidadeRoutingModule,
        PaginationModule.forRoot(),
        ModalModule.forRoot()
    ],
    declarations: [
        FuncionalidadeComponent,
        FuncionalidadeEditComponent
    ],
    providers: [
        FuncionalidadeService,
        FuncionalidadeResolver
    ],
    exports: []
})
export class FuncionalidadeModule { }
