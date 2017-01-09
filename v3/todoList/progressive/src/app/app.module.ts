import { NgModule, ErrorHandler } from '@angular/core';
import { IonicApp, IonicModule, IonicErrorHandler } from 'ionic-angular';

import { HttpModule } from '@angular/http';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

// import { HttpModule } from '@demoiselle/http';
import { AuthServiceProvider, SecurityModule } from '@demoiselle/security';
import { HttpServiceProvider } from '@demoiselle/http';

// Providers / Services
import { LoginService } from '../providers/login-service';
import { TodoService } from '../providers/todo-service';

// App Components
import { MyApp } from './app.component';
import { TodoListPage } from '../pages/todo-list/todo-list';
import { TodoFormPage } from '../pages/todo-form/todo-form';
import { LoginPage } from '../pages/login/login';
import { RegisterPage } from '../pages/register/register';

@NgModule({
  declarations: [
    MyApp,
    TodoListPage,
    TodoFormPage,
    LoginPage,
    RegisterPage
  ],
  imports: [
    IonicModule.forRoot(MyApp),
    HttpModule,
    FormsModule,
    SecurityModule,
    RouterModule.forRoot([], { useHash: true })
  ],
  exports: [RouterModule],
  bootstrap: [IonicApp],
  entryComponents: [
    MyApp,
    TodoListPage,
    TodoFormPage,
    LoginPage,
    RegisterPage
  ],
  providers: [
    { provide: ErrorHandler, useClass: IonicErrorHandler },
    HttpServiceProvider({
      endpoints: { main: 'http://localhost:8080/todo/api/' },
      multitenancy: false,
      unAuthorizedRoute: '/login',
      tokenKey: 'id_token'
    }),
    AuthServiceProvider({
      authEndpointUrl: '~main/',
      loginResourcePath: 'auth',
      tokenKey: 'id_token',
      loginRoute: '/login'
    }),
    LoginService,
    TodoService
  ]
})
export class AppModule { }
