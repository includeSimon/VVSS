import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserRegisterFormComponent } from './user-register-form/user-register-form.component';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";

import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { MatIconModule} from "@angular/material/icon";

import { ReactiveFormsModule } from '@angular/forms';
import {MatSelectModule} from "@angular/material/select";


@NgModule({
  declarations: [
    UserRegisterFormComponent
  ],
  exports: [
    UserRegisterFormComponent
  ],
  imports: [
    CommonModule,
    BrowserAnimationsModule,
    MatCardModule,
    MatButtonModule,
    MatInputModule,
    ReactiveFormsModule,
    MatIconModule,
    MatSelectModule
  ]
})
export class UserModule { }
