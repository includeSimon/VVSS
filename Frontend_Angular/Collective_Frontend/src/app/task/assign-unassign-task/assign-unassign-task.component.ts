import { Component, OnInit } from '@angular/core';
import {BackendRequestsService} from "../../backend-requests/backend-requests.service";

export interface Task {
  id: number;
  name: string;
  description: string;
  daysToCompleteTask: number;
  status: string;
  category: string;
  rewardPoints: number;

}

export interface User {
  id : number;
  firstName : string;
  userName: string;
  lastName: string;
  email: string;
  isAdmin: boolean;
  admin: boolean;
}

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

  constructor(private backendService: BackendRequestsService) { }

  ngOnInit(): void {
    this.backendService.get("http://localhost:8080/api/users/find-all").subscribe((payload) => {
      this.users = payload
    });

    this.backendService.get("http://localhost:8080/task/allTasks").subscribe((payload) => {
      this.tasks = payload;
    });

  }

  getUserTasks(username : string): void {
    // get tasks by username and set the datasource
    this.backendService.get("http://localhost:8080/task/find-by-username/" + username).subscribe((payload) => {
      this.userTasks = payload;
    });
  }

  onSubmit() {
    if (!this.selectedUser || !this.selectedTask) {
      return;
    }
    if (this.action === 'assign') {
      this.backendService.post("http://localhost:8080/task/assign-task/" + this.selectedTask + "/" + this.selectedUser, "").subscribe((payload) => {
        console.log(payload)
      });
    }
    else {
      this.backendService.post("http://localhost:8080/task/unassign-task/" + this.selectedTask + "/" + this.selectedUser, "").subscribe((payload) => {
        console.log(payload)
      });
    }

  }

  refresh(username : string) {
    this.backendService.get("http://localhost:8080/task/find-by-username/" + username).subscribe((payload) => {
      this.userTasks = payload;
    });
  }
}
