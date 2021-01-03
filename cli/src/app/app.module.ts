import {BrowserModule} from '@angular/platform-browser';
import {HttpClientModule} from '@angular/common/http';
import {NgModule} from '@angular/core';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {IndexPageComponent} from './index-page/index-page.component';
import {ErrorPageComponent} from './error-page/error-page.component';
import {LoginPageComponent} from './login-page/login-page.component';
import {HeaderViewComponent} from './view/header-view/header-view.component';
import {FontAwesomeModule} from '@fortawesome/angular-fontawesome';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {RegisterPageComponent} from './register-page/register-page.component';
import {PanelPageComponent} from './panel-page/panel-page.component';
import {AdminUserPageComponent} from './admin-user-page/admin-user-page.component';
import {InputViewComponent} from './view/input-view/input-view.component';
import {QuestPageComponent} from './quest-page/quest-page.component';
import {QuestCreatePageComponent} from './quest-create-page/quest-create-page.component';

@NgModule({
  declarations: [
    AppComponent,
    IndexPageComponent,
    ErrorPageComponent,
    LoginPageComponent,
    HeaderViewComponent,
    RegisterPageComponent,
    PanelPageComponent,
    AdminUserPageComponent,
    InputViewComponent,
    QuestPageComponent,
    HeaderViewComponent,
    QuestCreatePageComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FontAwesomeModule,
    ReactiveFormsModule,
    FormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
