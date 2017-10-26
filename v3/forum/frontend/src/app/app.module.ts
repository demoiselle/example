import { NgModule, ApplicationRef } from '@angular/core';
import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { CoreModule } from './core/core.module';

// feature modules
// import { LoginModule } from './login/login.module';
// import { HomeModule } from './home/home.module';
// import { UserModule } from './user';

@NgModule({
  imports: [
    AppRoutingModule,
    CoreModule.forRoot(),

    // #feature modules
    //LoginModule,
    //HomeModule,
    //UserModule
    
  ],
  declarations: [
    AppComponent
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
