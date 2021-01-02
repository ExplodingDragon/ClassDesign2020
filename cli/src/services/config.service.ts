import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ConfigService {
  email = '';
  admin = false;

  constructor() {
    this.loadLocal();
  }

  baseUrl = 'http://127.0.0.1:8088';
  token = '';

  userName = '';

  isLogin(): boolean {
    return !(this.token === '');
  }

  loadLocal(): void {
    if (localStorage.getItem('token') != null) {
      this.token = localStorage.getItem('token');
      this.email = localStorage.getItem('email');
      this.userName = localStorage.getItem('name');
      this.admin = Boolean(localStorage.getItem('admin'));
    }
  }

  logout(): void {
    this.token = '';
    localStorage.removeItem('token');
    localStorage.removeItem('userName');
  }

  save(data): void {
    this.token = data.token;
    this.email = data.email;
    this.userName = data.name;
    this.admin = data.admin;
    localStorage.setItem('token', data.token);
    localStorage.setItem('email', data.email);
    localStorage.setItem('name', data.name);
    localStorage.setItem('admin', data.admin);
  }
}
