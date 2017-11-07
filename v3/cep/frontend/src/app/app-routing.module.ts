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
