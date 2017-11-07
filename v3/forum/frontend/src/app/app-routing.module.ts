import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
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
                          path: 'user',
                          loadChildren: './user/user.module#UserModule',
                          data: {
                            title: 'User',
                            showInSidebar: true,
                            icon: 'icon-diamond'
                          }
                        },
                        
		
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
                          path: 'permissao',
                          loadChildren: './permissao/permissao.module#PermissaoModule',
                          data: {
                            title: 'Permissao',
                            showInSidebar: true,
                            icon: 'icon-diamond'
                          }
                        },
                        
		
                        {
                          path: 'perfil',
                          loadChildren: './perfil/perfil.module#PerfilModule',
                          data: {
                            title: 'Perfil',
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
                          path: 'funcionalidade',
                          loadChildren: './funcionalidade/funcionalidade.module#FuncionalidadeModule',
                          data: {
                            title: 'Funcionalidade',
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
        path: 'dashboard',
        loadChildren: './dashboard/dashboard.module#DashboardModule',
        data: {
          title: 'Dashboard',
          showInSidebar: true,
          icon: 'icon-speedometer'
        },
      }
      
    ]
  },
  {
    path: '',
    component: SimpleLayout,
    data: {
      title: 'Pages'
    },
    children: [
      {
        path: 'login',
        loadChildren: './auth/login/login.module#LoginModule',
      },
      {
        path: 'amnesia',
        loadChildren: './auth/amnesia/amnesia.module#AmnesiaModule',
      },
      {
        path: 'register',
        loadChildren: './auth/register/register.module#RegisterModule',
      }
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
