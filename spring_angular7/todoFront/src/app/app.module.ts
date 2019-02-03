import { HardCodedAuthenticationService } from './services/hard-coded-authentication.service';
import { LogoutComponent } from './components/logout/logout.component';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule } from '@angular/forms';
import { AppComponent } from './app.component';
import { Type } from '@angular/compiler';
import { WelcomeComponent } from './components/welcome/welcome.component';
import { LoginComponent } from './components/login/login.component';
import { ListTodosComponent } from './components/list-todos/list-todos.component';
import { ErrorComponent } from './components/error/error.component';
import { DateFormatPipe } from './pipes/date-format.pipe';
import { FooterComponent } from './components/footer/footer.component';
import { MenuComponent } from './components/menu/menu.component';

let INITIAL_COMPONENTS = [AppComponent,WelcomeComponent,LoginComponent,ListTodosComponent,ErrorComponent,MenuComponent,FooterComponent,LogoutComponent];


@NgModule({
  declarations: INITIAL_COMPONENTS,
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule
  ],
  providers: [DateFormatPipe,HardCodedAuthenticationService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
