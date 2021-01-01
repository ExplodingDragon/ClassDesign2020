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
import {ReactiveFormsModule} from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    IndexPageComponent,
    ErrorPageComponent,
    LoginPageComponent,
    HeaderViewComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FontAwesomeModule,
    ReactiveFormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
