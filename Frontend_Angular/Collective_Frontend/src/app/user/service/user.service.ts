import { Injectable } from '@angular/core';
import {BackendRequestsService} from "../../backend-requests/backend-requests.service";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private backendService: BackendRequestsService) { }

  registerUser(user: any): Observable<any> {
    return this.backendService.post("http://localhost:8080/register", user);
  }
}
