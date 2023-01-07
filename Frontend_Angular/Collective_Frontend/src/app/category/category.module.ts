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
import {MatNativeDateModule, MatRippleModule} from "@angular/material/core";
import {MatCardModule} from "@angular/material/card";
import { CategoryListComponent } from './category-list/category-list.component';
import { MatPaginatorModule } from '@angular/material/paginator';
import {ModifyCategoryComponent} from "./modify-category/modify-category.component";
import {MatToolbar, MatToolbarModule} from "@angular/material/toolbar";
import {MatCheckboxModule} from "@angular/material/checkbox";
import {MatGridListModule} from "@angular/material/grid-list";
import {MatMenuModule} from "@angular/material/menu";
import {NgMultiSelectDropDownModule} from "ng-multiselect-dropdown";
import {LayoutModule} from "@angular/cdk/layout";


@NgModule({
  declarations: [
    AddCategoryComponent,
    CategoryComponent,
    CategoryListComponent,
    ModifyCategoryComponent
  ],
    exports: [
        AddCategoryComponent,
        CategoryComponent,
        CategoryListComponent,
      ModifyCategoryComponent
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
    MatPaginatorModule,

    MatTableModule,
    MatCheckboxModule,
    MatFormFieldModule,
    MatSortModule,
    MatInputModule,
    MatNativeDateModule,
    MatIconModule,
    MatSelectModule,
    MatRadioModule,
    MatButtonModule,
    ReactiveFormsModule,
    MatCardModule,
    MatGridListModule,
    MatMenuModule,
    LayoutModule,
    MatToolbarModule,
    NgMultiSelectDropDownModule.forRoot()
  ]
})
export class CategoryModule { }
