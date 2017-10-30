import { NgModule } from '@angular/core';

import { PermissaoRoutingModule } from './permissao-routing.module';
import { SharedModule } from '../shared';

import { PermissaoService } from './permissao.service';
import { PermissaoComponent } from './permissao.component';
import { PermissaoEditComponent } from './permissao-edit.component';
import { PermissaoResolver } from './permissao.resolver';

@NgModule({
    imports: [
        SharedModule,
        PermissaoRoutingModule
    ],
    declarations: [
        PermissaoComponent,
        PermissaoEditComponent
    ],
    providers: [
        PermissaoService,
        PermissaoResolver
    ],
    exports: []
})
export class PermissaoModule { }
