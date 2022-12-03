import {Component, Input, OnInit} from '@angular/core';
import {Category} from "../models/category";
import {CategoryServiceService} from "../service/category-service.service";
import {Colorcategory} from "../enum/colorcategory.enum";

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent implements OnInit {

  @Input()
  public category!: Category;


  constructor(public categoryService: CategoryServiceService) {
  }

  ngOnInit(): void {
  }

}
