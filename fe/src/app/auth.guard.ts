import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { TokenService } from './token.service';

@Injectable({
  providedIn: 'root',
})
export class AuthGuard {
  constructor(private tokenService: TokenService, private router: Router) {}

  canActivate(): boolean {
    const token = this.tokenService.getToken();
    console.log('token: ', token);

    if (token) {
      return true; // Cho phép truy cập
    } else {
      this.router.navigate(['/login']); // Chuyển hướng đến trang đăng nhập nếu chưa đăng nhập
      return false;
    }
  }
}
