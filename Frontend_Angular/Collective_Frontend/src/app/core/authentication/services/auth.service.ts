import { Injectable } from '@angular/core';
import {Router} from "@angular/router";
import {BackendRequestsService} from "../../../backend-requests/backend-requests.service";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private backendService: BackendRequestsService,
              private router: Router) { }

  isLoggedIn() {
    return false;
  }

  login(data: any): void {

  }
}
