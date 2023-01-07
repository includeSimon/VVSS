import { Injectable } from '@angular/core';
import {MatDialog} from "@angular/material/dialog";
import {CategoryListComponent} from "../category-list/category-list.component";
import {ModifyCategoryComponent} from "../modify-category/modify-category.component";

@Injectable({
  providedIn: 'root'
})
export class CategoryServiceService {

  constructor(private dialog: MatDialog) { }

  openDialog(row: any): void {
    let dialogRef = this.dialog.open(ModifyCategoryComponent, {
      width: '600px', height: '850',
      data:  row
    });

    dialogRef.afterClosed().subscribe(result => {row = result;});
    console.log('Row clicked: ', row);
  }
}
