import { Component, OnInit } from '@angular/core';

export interface Task {
  task_name: string;
  task_category: string;
  task_deadline: string;
}

export interface User {
  username: string;
  firstName: string;
  lastName: string;
  isAdmin?: boolean;
  email?: string;
}

const TASK_DATA: Task[] = [
  {task_name: 'task1', task_category: 'task1', task_deadline: 'deadline1'},
  {task_name: 'task2', task_category: 'task2', task_deadline: 'deadline2'},
  {task_name: 'task3', task_category: 'task3', task_deadline: 'deadline3'},
  {task_name: 'task4', task_category: 'task4', task_deadline: 'deadline4'},
  {task_name: 'task5', task_category: 'task5', task_deadline: 'deadline5'},
  {task_name: 'task6', task_category: 'task6', task_deadline: 'deadline6'},
];

const USER_DATA: User[] = [
  {username: 'user1', firstName: '...', lastName: '...'},
  {username: 'user2', firstName: '...', lastName: '...'},
  {username: 'user3', firstName: '...', lastName: '...'},
  {username: 'user4', firstName: '...', lastName: '...'},
  {username: 'user5', firstName: '...', lastName: '...'},
  {username: 'user6', firstName: '...', lastName: '...'},
];


@Component({
  selector: 'assign-unassign-task',
  templateUrl: './assign-unassign-task.component.html',
  styleUrls: ['./assign-unassign-task.component.css']
})
export class AssignUnassignTaskComponent implements OnInit {

  displayedColumns : String[] = ['task_name', 'task_category', 'task_deadline'];
  tasks: Task[];
  users: User[];
  userTasks: Task[];
  selectedUser: any;
  selectedTask: any;
  action: any;

  constructor() { }

  ngOnInit(): void {
    this.tasks = TASK_DATA;
    this.users = USER_DATA;
  }

  getUserTasks(username : string): void {
    // get tasks by username and set the datasource
    console.log(username);
    // call to backend instead findTaskByUsername()
    this.userTasks = TASK_DATA;
  }

  onSubmit() {
    if (!this.selectedUser || !this.selectedTask) {
      return;
    }

    if (this.action === 'assign') this.userTasks.push(this.tasks.filter(task => task.task_name === this.selectedTask)[0]);
    else this.userTasks = this.userTasks.filter(task => task.task_name !== this.selectedTask);

  }

  refresh() {
    this.userTasks = this.userTasks.filter(task => task);
  }
}
