import { Injectable } from '@angular/core';
import {Router} from "@angular/router";
import jwtDecode from "jwt-decode";
import {BackendRequestsService} from "../../../backend-requests/backend-requests.service";
import {User} from "../../../task/assign-unassign-task/assign-unassign-task.component";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  user: User;
  private token: string;

  constructor(private backendService: BackendRequestsService,
              private router: Router) { }

  login(data: any): void {
    this.backendService.post("http://localhost:8080/auth/login", data)
      .subscribe(({ token }) => {

          sessionStorage.setItem('loggedIn', 'true');
          this.setToken(token);
          this.decodeJWT(token);
          this.router.navigate(['/home']).then();
        }
      );

  }

  setToken(token: string): void {
    sessionStorage.setItem('token', token);
  }

  getToken(): string {
    return sessionStorage.getItem('token')!;
  }

  getUserName(): string {
    return sessionStorage.getItem('user')!;

  }

  setUserName(userName: string): void {
    sessionStorage.setItem('user', userName);
  }

  logout(): void {
    if (this.getToken()) {
      this.backendService.post("http://localhost:8080/auth/logout", null).subscribe();
      sessionStorage.setItem('loggedIn', 'false');
      sessionStorage.removeItem('token');
      this.router.navigate(['/login']).then();
      sessionStorage.clear();
    }
  }

  isLoggedIn(): boolean {
    return sessionStorage.getItem('loggedIn') === 'true';
  }

  private decodeJWT(token: string): void {
    this.token = token;
    const decodedToken: unknown & { sub: string } = jwtDecode(token);
    const { sub: userName } = decodedToken;
    this.setUserName(userName);
    this.fetchUserByUsername(userName);
  }


  private fetchUserByUsername(userName: string) {
    this.backendService.get(`http://localhost:8080/api/users/find-by-username/${ userName }`)
      .subscribe();
  }
}
