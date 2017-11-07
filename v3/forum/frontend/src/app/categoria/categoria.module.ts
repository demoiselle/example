import { NgModule } from '@angular/core';

import { CategoriaRoutingModule } from './categoria-routing.module';
import { SharedModule } from '../shared';

import { CategoriaService } from './categoria.service';
import { CategoriaComponent } from './categoria.component';
import { CategoriaEditComponent } from './categoria-edit.component';
import { CategoriaResolver } from './categoria.resolver';
import { ModalModule, PaginationModule } from 'ngx-bootstrap';

@NgModule({
    imports: [
        SharedModule,
        CategoriaRoutingModule,
        PaginationModule.forRoot(),
        ModalModule.forRoot()
    ],
    declarations: [
        CategoriaComponent,
        CategoriaEditComponent
    ],
    providers: [
        CategoriaService,
        CategoriaResolver
    ],
    exports: []
})
export class CategoriaModule { }
