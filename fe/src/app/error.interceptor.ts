import { Injectable } from '@angular/core';
import {
  HttpInterceptor,
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpResponse,
  HttpErrorResponse,
} from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Router } from '@angular/router';

@Injectable()
export class ErrorInterceptor implements HttpInterceptor {
  constructor(private router: Router) {}

  intercept(
    request: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    return next.handle(request).pipe(
      catchError((error: HttpErrorResponse) => {
        let errorMessage = 'An error occurred';
        if (error.error instanceof ErrorEvent) {
          // Xử lý lỗi từ client-side
          errorMessage = error.error.message;
        } else {
          // Xử lý lỗi từ server-side
          errorMessage = error.error.message || errorMessage;
        }
        // Xử lý thông báo lỗi và redirect (nếu cần)
        this.router.navigateByUrl('/error');
        console.error(errorMessage);
        return throwError(errorMessage);
      })
    );
  }
}
