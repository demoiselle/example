import { NgModule } from '@angular/core';

import { ConfirmationPopoverModule } from 'angular-confirmation-popover';
import { CategoriaRoutingModule } from './categoria-routing.module';
import { SharedModule } from '../shared';

import { CategoriaService } from './categoria.service';
import { CategoriaComponent } from './categoria.component';
import { CategoriaEditComponent } from './categoria-edit.component';

@NgModule({
    imports: [
        SharedModule,
        CategoriaRoutingModule,
        ConfirmationPopoverModule.forRoot({
            confirmText: 'Sim',
            cancelText: 'Não',
            appendToBody: true
        })
    ],
    declarations: [
        CategoriaComponent, CategoriaEditComponent
    ],
    providers: [CategoriaService],
    exports: []
})
export class CategoriaModule { }
