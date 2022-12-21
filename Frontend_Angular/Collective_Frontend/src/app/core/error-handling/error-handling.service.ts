import { Injectable } from '@angular/core';
import { ToastrService } from "ngx-toastr";
import { HttpErrorResponse } from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ErrorHandlingService {

  constructor(private toastrService: ToastrService) {
  }

  handleError(error: HttpErrorResponse): void {
    const genericError = 'Something went wrong';
    const authErrors = ['INCORRECT_PASSWORD', 'USERNAME_NOT_FOUND', 'USERNAME_TAKEN'];

    let errorMessage: string;
    if (error.error instanceof ErrorEvent) {
      errorMessage = error.error.message;
    } else {
      switch (error.status) {
        case 401:
          if (authErrors.includes(error.error.errorCode)) {
            errorMessage = error.error.message;
          } else {
            errorMessage = genericError;
          }
          break;
        case 500:
          errorMessage = genericError;
          break;
        default:
          errorMessage = genericError;
      }
    }
    this.toastrService.error(errorMessage, "Warning", {
      timeOut: 3000,
      closeButton: true
    });
  }
}
