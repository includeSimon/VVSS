import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { TaskServiceService } from '../service/task-service.service';
import { AngularFireAuth } from '@angular/fire/auth';

@Component({
  selector: 'app-modify-task',
  templateUrl: './modify-task.component.html',
  styleUrls: ['./modify-task.component.css']
})
export class ModifyTaskComponent implements OnInit {
  form: FormGroup;
  task: any = {};
  categories: string[] = ['Personal', 'Work', 'Shopping', 'Other'];
  statuses: string[] = ['New', 'In Progress', 'Done', 'Rejected'];

  constructor(
    private fb: FormBuilder,
    private taskService: TaskServiceService,
    private afAuth: AngularFireAuth,
    private router: Router
  ) {}

  ngOnInit() {
    this.form = this.fb.group({
      description: ['', Validators.required],
      category: ['', Validators.required],
      status: ['', Validators.required]
    });

    this.taskService.getTask(id).subscribe(task => {
      this.task = task;
      this.form.patchValue(task);
    });
  }

  onSubmit() {
    if (this.form.valid) {
      this.taskService
        .updateTask(this.task.id, this.form.value)
        .then(() => this.router.navigate(['/']))
        .catch(error => console.error(error));
    }
  }

  deleteTask() {
    this.taskService
      .deleteTask(this.task.id)
      .then(() => this.router.navigate(['/']))
      .catch(error => console.error(error));
  }

  signOut() {
    this.afAuth.signOut();
    this.router.navigate(['/']);
  }
}
