import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {IndexPageComponent} from './index-page/index-page.component';
import {ErrorPageComponent} from './error-page/error-page.component';
import {LoginPageComponent} from './login-page/login-page.component';
import {RegisterPageComponent} from './register-page/register-page.component';
import {PanelPageComponent} from './panel-page/panel-page.component';
import {AdminUserPageComponent} from './admin-user-page/admin-user-page.component';
import {QuestPageComponent} from './quest-page/quest-page.component';
import {QuestCreatePageComponent} from './quest-create-page/quest-create-page.component';
import {QuestionDetailsPageComponent} from './question-details-page/question-details-page.component';
import {AskQuestionPageComponent} from './ask-question-page/ask-question-page.component';

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
    path: 'create', component: QuestCreatePageComponent
  },
  {
    path: 'question/:id/:type', component: QuestionDetailsPageComponent
  },
  {
    path: 'ask/:id', component: AskQuestionPageComponent
  },
  {
    path: 'users', component: AdminUserPageComponent
  },
  {
    path: 'quest', component: QuestPageComponent
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
