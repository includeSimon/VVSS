import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LoginComponent} from "./core/authentication/components/login/login.component";
import {UserRegisterFormComponent} from "./user/user-register-form/user-register-form.component";
import {AppComponent} from "./app.component";

const routes: Routes = [
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full'
  },
  {path: 'home', component: LoginComponent},
  {path: 'login', component: LoginComponent},
  {
    path: 'register',
    component: UserRegisterFormComponent,
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
