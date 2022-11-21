import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../../services/auth.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  loginValid: boolean;

  constructor(private formBuilder: FormBuilder, private authService: AuthService) { }

  ngOnInit(): void {
    this.setUpForm();
  }

  get formValid(): boolean {
    return this.loginForm.valid;
  }

  login(): void {
    if (this.loginForm.invalid) {
      return;
    }
    this.authService.login(this.loginForm.getRawValue())

  }

  isLoggedIn(): boolean {
    return this.authService.isLoggedIn();
  }

  private setUpForm(): void {
    this.loginForm = this.formBuilder.group({
      username: new FormControl('', Validators.required),
      password: new FormControl('', Validators.required),
      captcha: new FormControl('', Validators.required)
    })
  }
}
