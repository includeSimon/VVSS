import {Component, Inject, OnInit} from '@angular/core';
import {Category} from "../models/category";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MatTableDataSource} from "@angular/material/table";
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from "@angular/material/dialog";
import {CategoryServiceService} from "../service/category-service.service";
import {Router} from "@angular/router";
import {Colorcategory} from "../enum/colorcategory.enum";

@Component({
  selector: 'app-modify-category',
  templateUrl: './modify-category.component.html',
  styleUrls: ['./modify-category.component.css']
})
export class ModifyCategoryComponent implements OnInit {

  public categoryForm: FormGroup;
  public categoryList: Category[];

  public displayedColumns: string[] = ['nameCategory', 'colorCategory'];
  public dataSource = new MatTableDataSource();

  dropdownList: any;
  dropdownSettings: any;

  constructor(public dialogService: MatDialog, public categoryService: CategoryServiceService,
              public dialogRef: MatDialogRef<ModifyCategoryComponent>,@Inject(MAT_DIALOG_DATA) public data: any,
              private formBuilder: FormBuilder,private router: Router) { }

  ngOnInit(): void {
    this.categoryForm = this.formBuilder.group({
      nameCategory: [this.data.nameCategory, [Validators.required]],
      colorCategory: [this.data.colorCategory, [Validators.required]],
    });
    this.dropdownList = this.getData();
    this.dropdownSettings = {
      singleSelection: false,
      idField: 'id',
      textField: 'name'
    };
  }

  getData() : Array<any>{
    return [
      {id: '1', name: 'Red'},
      {id: '2', name: 'Blue'},
      {id: '3', name: 'Yellow'},
      {id: '4', name: 'Pink'},
    ];
  }

  onEdit(event: any) {
    // console.log(this.data);
    // console.log(event);
  }

  onItemSelect($event: any) {
      console.log('$event is ', $event);
  }

  color: Colorcategory;
  keys(): Array<string>{
    var keys = Object.keys(this.color);
    return keys;
  }
}
