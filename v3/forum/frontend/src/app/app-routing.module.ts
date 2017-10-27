import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserComponent } from './user';
import { AuthGuard } from '@demoiselle/security';

// Import Containers
import {
  FullLayout,
  SimpleLayout
} from './shared/layout/containers';

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'dashboard',
    pathMatch: 'full',
  },
  {
    path: '',
    component: FullLayout,
    data: {
      title: 'Home'
    },
    children: [

      {
        path: 'topico',
        loadChildren: './topico/topico.module#TopicoModule',
        data: {
          title: 'Topico',
          showInSidebar: true,
          icon: 'icon-diamond'
        }
      },


      {
        path: 'moderator',
        loadChildren: './moderator/moderator.module#ModeratorModule',
        data: {
          title: 'Moderator',
          showInSidebar: true,
          icon: 'icon-diamond'
        }
      },


      {
        path: 'mensagem',
        loadChildren: './mensagem/mensagem.module#MensagemModule',
        data: {
          title: 'Mensagem',
          showInSidebar: true,
          icon: 'icon-diamond'
        }
      },


      {
        path: 'guest',
        loadChildren: './guest/guest.module#GuestModule',
        data: {
          title: 'Guest',
          showInSidebar: true,
          icon: 'icon-diamond'
        }
      },


      {
        path: 'categoria',
        loadChildren: './categoria/categoria.module#CategoriaModule',
        data: {
          title: 'Categoria',
          showInSidebar: true,
          icon: 'icon-diamond'
        }
      },


      {
        path: 'user',
        loadChildren: './user/user.module#UserModule',
        data: {
          title: 'User',
          showInSidebar: true,
          icon: 'icon-diamond'
        }
      },
    ]
  }
];


@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
