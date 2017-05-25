import { NgModule } from '@angular/core';

import { ConfirmationPopoverModule } from 'angular-confirmation-popover';
import { LivroRoutingModule } from './livro-routing.module';
import { SharedModule } from '../shared';

import { LivroService } from './livro.service';
import { LivroComponent } from './livro.component';
import { LivroEditComponent } from './livro-edit.component';

@NgModule({
    imports: [
        SharedModule,
        LivroRoutingModule,
        ConfirmationPopoverModule.forRoot({
            confirmText: 'Sim',
            cancelText: 'Não',
            appendToBody: true
        })
    ],
    declarations: [
        LivroComponent, LivroEditComponent
    ],
    providers: [LivroService],
    exports: []
})
export class LivroModule { }
