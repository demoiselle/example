import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AuthGuard } from '@demoiselle/security';
import { CepComponent } from './cep.component';

@NgModule({
    imports: [
        RouterModule.forChild([
            {
                path: 'cep',
                data: ['Cep'],
                // canActivate: [AuthGuard],
                component: CepComponent
            }
        ])
    ],
    exports: [RouterModule]
})
export class CepRoutingModule { }
