import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LoginComponent} from "./core/authentication/components/login/login.component";
import {UserRegisterFormComponent} from "./user/user-register-form/user-register-form.component";
import {AppComponent} from "./app.component";
import {AssignUnassignTaskComponent} from "./task/assign-unassign-task/assign-unassign-task.component";
import {AddCategoryComponent} from "./category/add-category/add-category.component";
import {UnmarkedTaskListComponent} from "./task/unmarked-task-list/unmarked-task-list.component";

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
    path: 'task-list',
    component: UnmarkedTaskListComponent,
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
