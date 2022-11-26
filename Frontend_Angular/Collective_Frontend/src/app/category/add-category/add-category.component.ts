import { Component, OnInit } from '@angular/core';
import {Category} from "../models/category";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Colorcategory} from "../enum/colorcategory.enum";
import {CategoryServiceService} from "../service/category-service.service";

@Component({
  selector: 'app-add-category',
  templateUrl: './add-category.component.html',
  styleUrls: ['./add-category.component.css']
})
export class AddCategoryComponent implements OnInit {

  categoryList: Category[] = [];

  public categoryForm!: FormGroup;

  colorCategory = Colorcategory;

  keys(): Array<string>{
    var keys = Object.keys(this.colorCategory);
    return keys;
  }

  constructor(private categorySerice: CategoryServiceService, private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.initForm();

  }

  initForm(){
    this.categoryForm = this.formBuilder.group({
      nameCategory: ['', Validators.required],
      colorCategory: ['', [Validators.required]]
    })
  }

  onSubmit(){
  }

}
