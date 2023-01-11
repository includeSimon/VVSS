import {LoginComponent} from "./authentication/components/login/login.component";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatCardModule} from "@angular/material/card";
import {CommonModule} from "@angular/common";
import {MatButtonModule} from "@angular/material/button";
import {MatInputModule} from "@angular/material/input";
import {AuthService} from "./authentication/services/auth.service";
import {NgModule} from "@angular/core";
import {AppRoutingModule} from "../app-routing.module";
import {ToastrModule} from "ngx-toastr";
import {HTTP_INTERCEPTORS} from "@angular/common/http";
import {ErrorHandlingInterceptor} from "./error-handling/error-handling.interceptor";
import {AuthInterceptor} from "./authentication/services/auth.interceptor.service";

@NgModule({
  declarations: [LoginComponent],
  imports: [
    FormsModule,
    CommonModule,
    MatCardModule,
    MatButtonModule,
    MatInputModule,
    ReactiveFormsModule,
    AppRoutingModule,
    ToastrModule.forRoot({
      preventDuplicates: true,
      maxOpened: 1
    })
  ],
  providers: [
    AuthService,
    { provide: HTTP_INTERCEPTORS, useClass: ErrorHandlingInterceptor, multi: true},
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true},
  ],
  exports: [LoginComponent]

})
export class CoreModule {
}
