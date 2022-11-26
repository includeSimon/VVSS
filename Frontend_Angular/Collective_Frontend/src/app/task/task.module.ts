import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {AssignUnassignTaskComponent} from "./assign-unassign-task/assign-unassign-task.component";
import {MatTableModule} from "@angular/material/table";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatSelectModule} from "@angular/material/select";
import {FormsModule} from "@angular/forms";
import {MatCardModule} from "@angular/material/card";
import {MatButtonModule} from "@angular/material/button";



@NgModule({
  declarations: [
    AssignUnassignTaskComponent
  ],
  exports: [
    AssignUnassignTaskComponent
  ],
  imports: [
    CommonModule,
    MatTableModule,
    MatFormFieldModule,
    MatSelectModule,
    FormsModule,
    MatCardModule,
    MatButtonModule
  ]
})
export class TaskModule { }
