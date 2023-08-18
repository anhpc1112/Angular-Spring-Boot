import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FORM_LOGIN_VALIDATORS } from './Form-Validators';
import { FormlyFieldConfig } from '@ngx-formly/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  username: string = '';
  password: string = '';
  isSignUpClicked: boolean = false;
  formLoginConfig: FormlyFieldConfig[] = FORM_LOGIN_VALIDATORS;
  loginForm: FormGroup;

  firstName: string = '';
  lastName: string = '';
  email: string = '';

  constructor(private http: HttpClient, private router: Router) {
    this.loginForm = new FormGroup({});
    // this.loginForm = new FormGroup({
    //   username: new FormControl(this.username, [
    //     Validators.required,
    //     Validators.minLength(4),
    //   ]),
    // });
  }

  ngOnInit(): void {
    this.loginForm = new FormGroup({
      username: new FormControl(this.username, [
        Validators.required,
        Validators.minLength(4),
      ]),
    });
  }

  onSignIn() {
    const credentials = {
      email: this.username,
      password: this.password,
    };
    const validForm = this.loginForm.valid;
    console.log('form: ', validForm);
    if (this.loginForm.valid) {
      this.http
        .post<any>('http://localhost:8080/api/v1/auth/sign-in', credentials)
        .subscribe(
          (response) => {
            // Xử lý thành công đăng nhập
            this.router.navigateByUrl('/home');
          },
          (error) => {
            // Xử lý lỗi đăng nhập
            console.error(error);
          }
        );
    } else {
      this.http
        .post<any>('http://localhost:8080/api/v1/auth/sign-in', credentials)
        .subscribe(
          (response) => {
            // Xử lý thành công đăng nhập
            this.router.navigateByUrl('/home');
          },
          (error) => {
            // Xử lý lỗi đăng nhập
            console.error(error);
          }
        );
    }
  }

  onSignUp() {
    // this.isSignUpClicked = true;
    const credentials = {
      email: this.email,
      password: this.password,
      firstName: this.firstName,
      lastName: this.lastName,
    };
    console.log('data: ', credentials);
    // this.delay(20000).subscribe(() => {
    this.http
      .post<any>('http://localhost:8080/api/v1/auth/sign-up', credentials)
      .subscribe(
        (response) => {
          // Xử lý thành công đăng nhập
          this.router.navigateByUrl('/home');
        },
        (error) => {
          // Xử lý lỗi đăng nhập
          console.error(error);
        }
      );
    // });
  }

  backSignInPage() {
    this.isSignUpClicked = false;
  }

  onChangeSignUp() {
    this.isSignUpClicked = true;
  }

  get getUsername() {
    return this.loginForm.get('username');
  }

  private delay(ms: number): Observable<number> {
    return new Observable((observer) => {
      setTimeout(() => {
        observer.next(ms);
        observer.complete();
      }, ms);
    });
  }
}
