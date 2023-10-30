import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegisterComponent } from './MyComponents/register/register.component';
import { LoginComponent } from './MyComponents/login/login.component';
import { FormsModule } from '@angular/forms';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { DataService } from './service/data.service';
import { NavbarComponent } from './MyComponents/navbar/navbar.component';
import { WelcomepageComponent } from './MyComponents/welcomepage/welcomepage.component';
import { MyAccountComponent } from './MyComponents/my-account/my-account.component';

import { FooterComponent } from './MyComponents/footer/footer.component';

import { PageNotFoundComponent } from './MyComponents/page-not-found/page-not-found.component';
import { TokenInterceptor } from './service/token-interceptor.service';


@NgModule({
  declarations: [
    AppComponent,
    RegisterComponent,
    LoginComponent,
    NavbarComponent,
    WelcomepageComponent,


    FooterComponent,

    PageNotFoundComponent,


  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule, HttpClientModule
  ],
  providers: [
    {provide: HTTP_INTERCEPTORS,useClass:TokenInterceptor,multi:true}
    ,DataService],
  bootstrap: [AppComponent]
})
export class AppModule { }
