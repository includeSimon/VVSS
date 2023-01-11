import { Injectable } from '@angular/core';
import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { catchError, Observable } from 'rxjs';
import { ErrorHandlingService } from "./error-handling.service";

@Injectable()
export class ErrorHandlingInterceptor implements HttpInterceptor {

  constructor(private errorHandlingService: ErrorHandlingService) {
  }

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    return next.handle(request)
      .pipe(
        catchError((error: HttpErrorResponse) => {
          if (error.status !== undefined) {
            this.errorHandlingService.handleError(error);
          }
          return new Observable<HttpEvent<any>>();
        })
      )

  }
}

