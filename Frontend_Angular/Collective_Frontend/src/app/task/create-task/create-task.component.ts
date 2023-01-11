import { Component, OnInit } from '@angular/core';
import {Category} from "../../category/models/category";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Colorcategory} from "../../category/enum/colorcategory.enum";
import {CategoryServiceService} from "../../category/service/category-service.service";
import {TaskServiceService} from "../service/task-service.service";
import {Router} from "@angular/router";
import {first} from "rxjs";

@Component({
  selector: 'app-create-task',
  templateUrl: './create-task.component.html',
  styleUrls: ['./create-task.component.css']
})
export class CreateTaskComponent implements OnInit {

  categoryList: Category[] = [];

  public taskCreateForm!: FormGroup;

  colorCategory = Colorcategory;

  keys(): Array<string>{
    var keys = Object.keys(this.colorCategory);
    return keys;
  }

  task: any;
  fields!: any[];
  severities: any[] = [];
  date!: Date;

  submitted = false;

  constructor(private formBuilder:FormBuilder,  private httpService: TaskServiceService,
              private router: Router) {
  }

  category: any;


  ngOnInit(): void {
    this.initForm();

  }

  initForm(){

    this.taskCreateForm = this.formBuilder.group({
      name: [{ value: '', disabled: false} , [Validators.required,Validators.maxLength(30),Validators.minLength(1), Validators.pattern('^[a-zA-Z]+$')]],
      description: [{value: '', disabled: false}, [Validators.required, Validators.maxLength(500), Validators.minLength(200)]],
      category: [{value: '', disabled: false }, [Validators.required, Validators.maxLength(30),Validators.pattern('^\\+4(0[0-9]{9}|9[0-9]{6,13})$')]],
      targetDate: new Date(),
      status: [{value: '', disabled: false}],
      daysToComplete: [{ value: '', disabled: false} , [Validators.required, Validators.pattern('^[0-9]*$')]],
      rewardPoints: [{ value: '', disabled: false} , [Validators.required, Validators.pattern('^[0-9]*$')]],
    });
  }

  onSubmit(){
    this.submitted = true;
    if (this.taskCreateForm.invalid) { return; }
    let task = this.taskCreateForm.value;
    task.name = '';
    this.httpService.postTask(task)
      .pipe(first())
      .subscribe({
        next: async (result) => {
          if (result.error) {
            console.log("There is an error")
          }
          if (result.message) {
            console.log("Success")
            await new Promise(f => setTimeout(f, 1500));
            await this.router.navigate(["/add-category"]);
          }
        },
        error: () => {
          this.router.navigate(["/add-category"]);
        }
      })

  }
};
