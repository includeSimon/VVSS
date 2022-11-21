import { Component, OnInit } from '@angular/core';

export interface Task {
  task_name: string;
  task_category: string;
  task_deadline: string;
}

const TASK_DATA: Task[] = [
  {task_name: 'task1', task_category: 'washing', task_deadline: 'today'},
  {task_name: 'task2', task_category: 'sleep', task_deadline: 'tomorrow'},
  {task_name: 'task3', task_category: 'cook', task_deadline: 'today'},
  {task_name: 'task4', task_category: 'sleep', task_deadline: 'never'},
  {task_name: 'task5', task_category: 'eat', task_deadline: 'yet'},
  {task_name: 'task6', task_category: 'play', task_deadline: 'next week'},
];


@Component({
  selector: 'assign-unassign-task',
  templateUrl: './assign-unassign-task.component.html',
  styleUrls: ['./assign-unassign-task.component.css']
})
export class AssignUnassignTaskComponent implements OnInit {

  displayedColumns : String[] = ['task_name', 'task_category', 'task_deadline'];
  tasks: Task[] = TASK_DATA;

  constructor() { }

  ngOnInit(): void {
  }

}
