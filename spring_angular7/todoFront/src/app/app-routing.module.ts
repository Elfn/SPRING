import { RouteGuardService } from './services/route-guard.service';
import { ErrorComponent } from './components/error/error.component';
import { NgModule, Component } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { WelcomeComponent } from 'src/app/components/welcome/welcome.component';
import { LoginComponent } from 'src/app/components/login/login.component';
import { ListTodosComponent } from 'src/app/components/list-todos/list-todos.component';
import { LogoutComponent } from 'src/app/components/logout/logout.component';

const routes: Routes = [
  {path:'', component: LoginComponent},
  {path:'login', component: LoginComponent},
  {path:'welcome/:name', component: WelcomeComponent, canActivate: [RouteGuardService]},
  {path:'todos', component: ListTodosComponent, canActivate: [RouteGuardService]},
  {path:'logout', component: LogoutComponent, canActivate: [RouteGuardService]},
  {path:'**', component: ErrorComponent},
  
                      ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
