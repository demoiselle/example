import { NgModule } from '@angular/core';

import { TopicoRoutingModule } from './topico-routing.module';
import { SharedModule } from '../shared';

import { TopicoService } from './topico.service';
import { TopicoComponent } from './topico.component';
import { TopicoEditComponent } from './topico-edit.component';
import { TopicoResolver } from './topico.resolver';

@NgModule({
    imports: [
        SharedModule,
        TopicoRoutingModule
    ],
    declarations: [
        TopicoComponent,
        TopicoEditComponent
    ],
    providers: [
        TopicoService,
        TopicoResolver
    ],
    exports: []
})
export class TopicoModule { }
