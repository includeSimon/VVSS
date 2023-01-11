import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {MatIconModule} from '@angular/material/icon';
import {MatTableModule} from "@angular/material/table";
import {MatDialogModule} from "@angular/material/dialog";
import {MatButtonModule} from "@angular/material/button";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {MatSelectModule} from "@angular/material/select";
import {MatSortModule} from "@angular/material/sort";
import {MatRadioModule} from "@angular/material/radio";
import {MatButtonToggleModule} from "@angular/material/button-toggle";
import {MatNativeDateModule, MatRippleModule} from "@angular/material/core";
import {MatCardModule} from "@angular/material/card";
import {CreateTaskComponent} from "./create-task/create-task.component";
import {AssignUnassignTaskComponent} from "./assign-unassign-task/assign-unassign-task.component";
import {MatDatepickerModule} from "@angular/material/datepicker";
import { UnmarkedTaskListComponent } from './unmarked-task-list/unmarked-task-list.component';



@NgModule({
  declarations: [
    CreateTaskComponent,
    AssignUnassignTaskComponent,
    UnmarkedTaskListComponent
  ],
  exports: [
    AssignUnassignTaskComponent,
    CreateTaskComponent
  ],
  imports: [
    CommonModule,
    MatTableModule,
    MatDialogModule,
    MatButtonModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatSelectModule,
    MatSortModule,
    MatInputModule,
    MatButtonToggleModule,
    MatRadioModule,
    MatIconModule,
    MatRippleModule,
    FormsModule,
    MatCardModule,
    MatDatepickerModule,
    MatNativeDateModule
  ]
})
export class TaskModule { }
