import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AddCategoryComponent } from './add-category/add-category.component';
import { CategoryComponent } from './category/category.component';
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
import {MatRippleModule} from "@angular/material/core";
import {MatCardModule} from "@angular/material/card";



@NgModule({
  declarations: [
    AddCategoryComponent,
    CategoryComponent
  ],
  exports: [
    AddCategoryComponent,
    CategoryComponent
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
  ]
})
export class CategoryModule { }
