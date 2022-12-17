import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LoginComponent} from "./core/authentication/components/login/login.component";
import {UserRegisterFormComponent} from "./user/user-register-form/user-register-form.component";
import {AppComponent} from "./app.component";
import {AssignUnassignTaskComponent} from "./task/assign-unassign-task/assign-unassign-task.component";
import {AddCategoryComponent} from "./category/add-category/add-category.component";
import {CreateTaskComponent} from "./task/create-task/create-task.component";

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
  },
  {
    path: 'task-assignation',
    component: AssignUnassignTaskComponent,
    pathMatch: 'full'
  },
  {
    path: 'add-category',
    component: AddCategoryComponent,
    pathMatch: 'full'
  },
  {
    path: 'add-create-task',
    component: CreateTaskComponent,
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
