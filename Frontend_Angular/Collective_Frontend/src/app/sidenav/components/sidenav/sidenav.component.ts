import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {AuthService} from "../../../core/authentication/services/auth.service";

@Component({
  selector: 'app-sidenav',
  templateUrl: './sidenav.component.html',
  styleUrls: ['./sidenav.component.css']
})
export class SidenavComponent implements OnInit {

  constructor(private router: Router, private authService: AuthService) { }

  ngOnInit(): void {
  }

  openHome = () => {
    this.router.navigate(['/app-home']);
  }

  openTask = () => {
    this.router.navigate(['/app-create-task']);
  }

  openAssignTask = () => {
    this.router.navigate(['/assign-unassign-task']);
  }

  openCategory = () => {
    this.router.navigate(['/app-add-category']);
  }

  logout(): void {
    this.authService.logout();
  }

  isLoggedIn(): boolean {
    return this.authService.isLoggedIn();
  }
}
