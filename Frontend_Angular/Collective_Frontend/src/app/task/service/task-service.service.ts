import { Injectable } from '@angular/core';
import {BackendRequestsService} from "../../backend-requests/backend-requests.service";
import {Observable} from "rxjs";
import {environment} from "../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class TaskServiceService {

  constructor(private backendRequestsService: BackendRequestsService) { }

  getAllTasks(): Observable<Task[]> {
    return this.backendRequestsService.get(environment.apiUrl + '/task/allTasks');
  }

  postTask(task: Task) {
    return this.backendRequestsService.post( '/task/addTask', task);
  }
}
