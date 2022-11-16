import {LoginComponent} from "./authentication/components/login/login.component";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatCardModule} from "@angular/material/card";
import {CommonModule} from "@angular/common";
import {MatButtonModule} from "@angular/material/button";
import {MatInputModule} from "@angular/material/input";
import {AuthService} from "./authentication/services/auth.service";
import {NgModule} from "@angular/core";
import {AppRoutingModule} from "../app-routing.module";

@NgModule({
  declarations: [LoginComponent],
  imports: [
    FormsModule,
    CommonModule,
    MatCardModule,
    MatButtonModule,
    MatInputModule,
    ReactiveFormsModule,
    AppRoutingModule
  ],
  providers: [
    AuthService
  ],
  exports: [LoginComponent]

})
export class CoreModule {
}
