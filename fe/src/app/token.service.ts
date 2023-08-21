import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class TokenService {
  private token: string | null = null;

  constructor(private http: HttpClient) {}

  setToken(token: string): void {
    this.token = token;
  }

  getToken(): string | null {
    return this.token;
  }

  private getHeaders(): HttpHeaders {
    let headers = new HttpHeaders();
    if (this.token) {
      headers = headers.set('Authorization', `Bearer ${this.token}`);
    }
    return headers;
  }

  // Hàm gọi API với token
  apiGet<T>(url: string): Observable<T> {
    const headers = this.getHeaders();
    return this.http.get<T>(url, { headers });
  }

  apiPost<T>(url: string, body: any): Observable<T> {
    const headers = this.getHeaders();
    return this.http.post<T>(url, body, { headers });
  }

  // Thêm các hàm gọi API khác tương tự ở đây
}
