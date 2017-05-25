import { LivroModule } from './livro';
import { LivrariaModule } from './livraria';
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

// feature modules
import { LoginModule } from './login/login.module';
import { HomeModule } from './home/home.module';
import { UserModule } from './user';

@NgModule({
  imports: [
		LivroModule,

		LivrariaModule,

    BrowserModule,
    HttpModule,
    FormsModule,
    AppRoutingModule,
    CoreModule.forRoot(),
    SharedModule,
    SecurityModule,
    ToastyModule.forRoot(),
    // #feature modules
    LoginModule,
    HomeModule,
    UserModule
  ],
  declarations: [
    AppComponent
  ],
  providers: [
    ToastCommunicationService
  ],
  bootstrap: [
    AppComponent
  ]
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
