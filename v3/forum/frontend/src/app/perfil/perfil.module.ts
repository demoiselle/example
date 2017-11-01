import { NgModule } from '@angular/core';

import { PerfilRoutingModule } from './perfil-routing.module';
import { SharedModule } from '../shared';

import { PerfilService } from './perfil.service';
import { PerfilComponent } from './perfil.component';
import { PerfilEditComponent } from './perfil-edit.component';
import { PerfilResolver } from './perfil.resolver';
import { PaginationModule } from 'ngx-bootstrap';

@NgModule({
    imports: [
        SharedModule,
        PerfilRoutingModule,
        PaginationModule.forRoot()
    ],
    declarations: [
        PerfilComponent,
        PerfilEditComponent
    ],
    providers: [
        PerfilService,
        PerfilResolver
    ],
    exports: []
})
export class PerfilModule { }
