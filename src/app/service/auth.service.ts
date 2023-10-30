import { Observable, of, throwError } from 'rxjs';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { TokenInterceptor } from './token-interceptor.service';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(private router: Router,private tokenService:TokenInterceptor) {}

  setToken(token: string): void {
    localStorage.setItem('token', token);
  }

  getToken(): string | null {
    
    return localStorage.getItem('token');
  }

  isLoggedIn() {
    return this.getToken() !== "null";
  }

  logout() {
    for (let i = 0; i < localStorage.length; i++) {
      const key = localStorage.key(i)||"";
    //  const value:string = localStorage.getItem(key);
    
      if (localStorage.getItem(key) !== "null") {
        localStorage.setItem(key, String(null)); // or localStorage.setItem(key, value + '');
      }
    }
    
    
    this.tokenService.changeToken("null");
    // localStorage.removeItem('token');
    this.router.navigate(['login']);
  }

//   login({ email, password }: any): Observable<any> {
//     if (email === 'admin@gmail.com' && password === 'admin123') {
//       this.setToken(this.tokenService.token);
//       return of({ name: 'Tarique Akhtar', email: 'admin@gmail.com' });
//     }
//     return throwError(new Error('Failed to login'));
//   }
}