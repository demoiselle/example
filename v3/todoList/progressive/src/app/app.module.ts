// Angular
import { NgModule, ErrorHandler } from '@angular/core';
import { HttpModule } from '@angular/http';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
// Ionic
import { IonicApp, IonicModule, IonicErrorHandler } from 'ionic-angular';

// import { HttpModule } from '@demoiselle/http';
import { AuthServiceProvider, SecurityModule } from '@demoiselle/security';
import { HttpServiceProvider } from '@demoiselle/http';
import { MomentModule } from 'angular2-moment';

// Providers / Services
import { LoginService } from '../providers/login-service';
import { TodoService } from '../providers/todo-service';

// App Components
import { MyApp, AppEvents } from './app.component';
import { TodoListPage } from '../pages/todo-list/todo-list';
import { TodoFormPage } from '../pages/todo-form/todo-form';
import { LoginPage } from '../pages/login/login';
import { RegisterPage } from '../pages/register/register';

@NgModule({
  declarations: [
    MyApp,
    // Pages
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
    MomentModule,
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
      // endpoints: { main: 'http://localhost:8080/api/' },
      endpoints: { main: 'http://todolist-demoiselle.44fs.preview.openshiftapps.com/api/' },
      multitenancy: false,
      unAuthorizedRoute: (args) => {
        AppEvents.publish('auth:unauthorizred', args, Date.now());
      },
      tokenKey: 'id_token'
    }),
    AuthServiceProvider({
      authEndpointUrl: '~main/',
      loginResourcePath: 'auth',
      tokenKey: 'id_token',
      loginRoute: (args) => {
        AppEvents.publish('auth:login-success', args, Date.now());
      }
    }),
    LoginService,
    TodoService
  ]
})
export class AppModule { }