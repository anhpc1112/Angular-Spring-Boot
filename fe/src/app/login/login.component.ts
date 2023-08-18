import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FORM_LOGIN_VALIDATORS } from './Form-Validators';
import { FormlyFieldConfig } from '@ngx-formly/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

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
        .post<any>('http://localhost:8080/api/login', credentials)
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
    this.isSignUpClicked = true;
  }

  backSignInPage() {
    this.isSignUpClicked = false;
  }

  get getUsername() {
    return this.loginForm.get('username');
  }
}
