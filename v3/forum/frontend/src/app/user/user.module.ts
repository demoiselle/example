import { NgModule } from '@angular/core';

import { UserRoutingModule } from './user-routing.module';
import { SharedModule } from '../shared';

import { UserService } from './user.service';
import { UserComponent } from './user.component';
import { UserEditComponent } from './user-edit.component';
import { UserResolver } from './user.resolver';
import { PaginationModule } from 'ngx-bootstrap';

@NgModule({
    imports: [
        SharedModule,
        UserRoutingModule,
        PaginationModule.forRoot()
    ],
    declarations: [
        UserComponent,
        UserEditComponent
    ],
    providers: [
        UserService,
        UserResolver
    ],
    exports: []
})
export class UserModule { }
