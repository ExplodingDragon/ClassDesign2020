import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {IndexPageComponent} from './index-page/index-page.component';
import {ErrorPageComponent} from './error-page/error-page.component';
import {LoginPageComponent} from './login-page/login-page.component';

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
