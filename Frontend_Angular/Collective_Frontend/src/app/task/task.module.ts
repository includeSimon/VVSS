import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {AssignUnassignTaskComponent} from "./assign-unassign-task/assign-unassign-task.component";
import {MatTableModule} from "@angular/material/table";



@NgModule({
  declarations: [
    AssignUnassignTaskComponent
  ],
  exports: [
    AssignUnassignTaskComponent
  ],
  imports: [
    CommonModule,
    MatTableModule
  ]
})
export class TaskModule { }
