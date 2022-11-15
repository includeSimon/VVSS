import { Component, OnInit } from '@angular/core';
import {FormGroup, FormControl, Validators, AbstractControl, ValidationErrors, ValidatorFn} from "@angular/forms";
import {Observable, subscribeOn} from "rxjs";
import {BackendRequestsService} from "../../backend-requests/backend-requests.service";

@Component({
  selector: 'user-register-form',
  templateUrl: './user-register-form.component.html',
  styleUrls: ['./user-register-form.component.css']
})
export class UserRegisterFormComponent implements OnInit {

  hide: boolean = true;
  repeatHide: boolean = true;

  constructor(private backendService : BackendRequestsService ) { }

  ngOnInit(): void {
  }

  registerForm : FormGroup = new FormGroup(
    {
      email: new FormControl('',
        [
          Validators.required,
          Validators.email
        ]
      ),
      username: new FormControl('',
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
      firstname: new FormControl('',
        [
          Validators.required,
        ]
      ),
      lastname: new FormControl('',
        [
          Validators.required,
        ]
      ),
      tel: new FormControl('',
        [
          Validators.required,
          Validators.pattern('[0-9]+')
        ]
      )
    }
  )

  selectedRole: any = '';

  onSubmit() : void {
    if (this.registerForm.valid) {
      this.register(this.registerForm.value)
    }
  }

  register(data: any) { //: Observable<any>{
    let display : String = '';
    for (let control in data) {
      display += data[control.valueOf()] + '\n';
    }
    display += this.selectedRole;
    alert(display);
    //return this.backendService.post("", data,{responseType: 'text'} );
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
    return !!control.parent && !!control.parent.value && control.value === (control.parent?.controls as any)[matchTo].value ? null : { matching: true };
  };
}

