import {Component, OnInit} from '@angular/core';
import {AbstractControl, FormControl, FormGroup, ValidationErrors, ValidatorFn, Validators} from "@angular/forms";
import {UserService} from "../service/user.service";
import {Router} from "@angular/router";

@Component({
  selector: 'user-register-form',
  templateUrl: './user-register-form.component.html',
  styleUrls: ['./user-register-form.component.css']
})
export class UserRegisterFormComponent implements OnInit {

  hide: boolean = true;
  repeatHide: boolean = true;

  constructor(private userService: UserService, private router: Router) {
  }

  ngOnInit(): void {
  }

  registerForm: FormGroup = new FormGroup(
    {
      email: new FormControl('',
        [
          Validators.required,
          Validators.email
        ]
      ),
      userName: new FormControl('',
        [
          Validators.required,
        ]
      ),
      password: new FormControl('',
        [
          Validators.required,
          Validators.minLength(6)
        ]
      ),
      repeatPassword: new FormControl('',
        [
          Validators.required,
          matchValidator('password')
        ]
      ),
      firstName: new FormControl('',
        [
          Validators.required,
        ]
      ),
      lastName: new FormControl('',
        [
          Validators.required,
        ]
      )
      // tel: new FormControl('',
      //   [
      //     Validators.required,
      //     Validators.pattern('[0-9]+')
      //   ]
      // )
    }
  )

  selectedRole: any = '';

  onSubmit(): void {
    if (this.registerForm.valid) {
      this.register(this.registerForm.getRawValue())
    }
  }

  register(data: any): void {
    let display: String = '';
    for (let control in data) {
      display += data[control.valueOf()] + '\n';
    }
    display += this.selectedRole;
    alert(display);
    data.isAdmin = this.selectedRole === 'admin';
    delete data['repeatPassword'];
    console.log(this.selectedRole);
    console.log(data);
    this.userService.registerUser(data).subscribe(() => {
      this.router.navigate(['/login']).then()
    });
  }

}


export function matchValidator(
  matchTo: string,
  reverse?: boolean
): ValidatorFn {
  return (control: AbstractControl):
    ValidationErrors | null => {
    if (control.parent && reverse) {
      const c = (control.parent?.controls as any)[matchTo] as AbstractControl;
      if (c) {
        c.updateValueAndValidity();
      }
      return null;
    }
    return !!control.parent && !!control.parent.value && control.value === (control.parent?.controls as any)[matchTo].value ? null : {matching: true};
  };
}

