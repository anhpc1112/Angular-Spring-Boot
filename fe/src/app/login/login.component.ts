import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { TokenService } from '../token.service';
import { UserSignIn, UserSignUp } from './User';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  emailSignIn: string = '';
  passwordSignIn: string = '';
  isSignUpClicked: boolean = false;

  signInForm: FormGroup;
  signUpForm: FormGroup;

  firstName: string = '';
  lastName: string = '';
  emailSignUp: string = '';
  passwordSignUp: string = '';

  submittedSignIn: boolean = false;
  submittedSignUp: boolean = false;

  constructor(
    private http: HttpClient,
    private router: Router,
    private tokenService: TokenService
  ) {
    this.signInForm = new FormGroup({});
    this.signUpForm = new FormGroup({});
  }

  ngOnInit(): void {
    this.signInForm = new FormGroup({
      emailSignIn: new FormControl(this.emailSignIn, [Validators.required]),
      passwordSignIn: new FormControl(this.passwordSignIn, [
        Validators.required,
      ]),
    });

    this.signUpForm = new FormGroup({
      emailSignUp: new FormControl(this.emailSignUp, [
        Validators.required,
        Validators.email,
      ]),
      passwordSignUp: new FormControl(this.passwordSignUp, [
        Validators.required,
      ]),
      firstName: new FormControl(this.firstName, [Validators.required]),
      lastName: new FormControl(this.lastName, [Validators.required]),
    });
  }

  onSignIn() {
    this.submittedSignIn = true;
    const credentials: UserSignIn = {
      email: this.signInForm.controls['emailSignIn'].value,
      password: this.signInForm.controls['passwordSignIn'].value,
    };
    if (this.signInForm.valid) {
      this.http
        .post<any>('http://localhost:8081/api/v1/auth/sign-in', credentials)
        .subscribe(
          (response) => {
            // Xử lý thành công đăng nhập
            this.router.navigateByUrl('/home');
            console.log('Token back: ' + response.token);
            this.tokenService.setToken(response.token);
          },
          (error) => {
            // Xử lý lỗi đăng nhập
            console.error(error);
          }
        );
    }
  }

  onSignUp() {
    this.submittedSignUp = true;
    const credentials: UserSignUp = {
      email: this.signUpForm.controls['emailSignUp'].value,
      password: this.signUpForm.controls['passwordSignUp'].value,
      firstName: this.signUpForm.controls['firstName'].value,
      lastName: this.signUpForm.controls['lastName'].value,
    };
    console.log('data: ', credentials);

    if (this.signUpForm.valid) {
      this.http
        .post<any>('http://localhost:8081/api/v1/auth/sign-up', credentials)
        .subscribe(
          (response) => {
            // Xử lý thành công đăng nhập
            console.log('sucess: ' + response);
            this.isSignUpClicked = false;
            this.router.navigateByUrl('/login');
          },
          (error) => {
            // Xử lý lỗi đăng nhập
            console.error(error);
          }
        );
    }
  }

  backSignInPage() {
    this.isSignUpClicked = false;
  }

  onChangeSignUp() {
    this.isSignUpClicked = true;
  }
}
