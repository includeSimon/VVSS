// import { Component, OnInit } from '@angular/core';
import {AfterViewInit, Component, Input, OnInit, ViewChild} from '@angular/core';
import {Category} from "../models/category";
import {MatTableDataSource} from "@angular/material/table";
import {Colorcategory} from "../enum/colorcategory.enum";
import {CategoryServiceService} from "../service/category-service.service";
import {Router} from "@angular/router";
import {Validators} from "@angular/forms";
import {MatDialog} from "@angular/material/dialog";
import {MatPaginator} from "@angular/material/paginator";

@Component({
  selector: 'app-category-list',
  templateUrl: './category-list.component.html',
  styleUrls: ['./category-list.component.css']
})
export class CategoryListComponent implements OnInit, AfterViewInit {

  @Input()
  public categoryList: Category[];
  public category: any;

  Color: Colorcategory;

  displayedColumns: string[] = ['nameCategory', 'colorCategory', 'actions'];
  dataSource = new MatTableDataSource<Category>(ELEMENT_DATA);


  constructor(private router: Router,
              public categoryService: CategoryServiceService,
              private dialog: MatDialog) {
  }
  @ViewChild(MatPaginator) paginator: MatPaginator;

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }

  ngOnInit(): void {
  }

}

const ELEMENT_DATA: Category[] = [
  {nameCategory: 'SPALAT', colorCategory: Colorcategory.RED}
];
