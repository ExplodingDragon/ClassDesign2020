import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {IndexPageComponent} from './index-page/index-page.component';
import {ErrorPageComponent} from './error-page/error-page.component';
import {LoginPageComponent} from './login-page/login-page.component';
import {RegisterPageComponent} from './register-page/register-page.component';
import {PanelPageComponent} from './panel-page/panel-page.component';
import {AdminUserPageComponent} from './admin-user-page/admin-user-page.component';

const routes: Routes = [
  {
    path: '', redirectTo: '/index', pathMatch: 'full'
  },
  {
    path: 'index', component: IndexPageComponent
  },
  {
    path: 'login', component: LoginPageComponent
  },
  {
    path: 'register', component: RegisterPageComponent
  },
  {
    path: 'panel', component: PanelPageComponent
  },
  {
    path: 'users', component: AdminUserPageComponent
  },
  {
    path: '404', component: ErrorPageComponent
  },
  {
    path: '**', redirectTo: '/404', pathMatch: 'full'
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
