import { NgModule, ApplicationRef } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpModule } from '@angular/http';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { CoreModule } from './core/core.module';
import { SecurityModule } from '@demoiselle/security';
import { SharedModule } from './shared';
import { ToastyModule } from 'ng2-toasty';
import { ToastCommunicationService } from './shared/toast-communication.service';
import { removeNgStyles, createNewHosts } from '@angularclass/hmr';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

// feature modules
import { LoginModule } from './login/login.module';
import { HomeModule } from './home/home.module';
import { UserModule } from './user';
import { TodoModule } from './todo';

@NgModule({
  imports: [
    BrowserModule,
    HttpModule,
    FormsModule,
    AppRoutingModule,
    CoreModule.forRoot(),
    SharedModule,
    SecurityModule,
    ToastyModule.forRoot(),
    BrowserAnimationsModule,
    // #feature modules
    LoginModule,
    HomeModule,
    UserModule,
    TodoModule
  ],
  declarations: [
    AppComponent,
  ],
  providers: [ToastCommunicationService], // { provide: ComponentsHelper, useClass: ComponentsHelper }],
  bootstrap: [AppComponent]
})
export class AppModule {
  constructor(public appRef: ApplicationRef) { }
  hmrOnInit(store) {
    console.log('HMR store:', store);
  }
  hmrOnDestroy(store) {
    let cmpLocation = this.appRef.components.map(cmp => cmp.location.nativeElement);
    // recreate elements
    store.disposeOldHosts = createNewHosts(cmpLocation);
    // remove styles
    removeNgStyles();
  }
  hmrAfterDestroy(store) {
    // display new elements
    store.disposeOldHosts();
    delete store.disposeOldHosts;
  }
}
