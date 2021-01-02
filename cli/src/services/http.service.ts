import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {catchError} from 'rxjs/operators';
import {Result} from '../models/global.model';
import {ConfigService} from './config.service';

@Injectable({
  providedIn: 'root'
})
export class HttpService {

  constructor(
    private http: HttpClient,
    private config: ConfigService
  ) {
  }

  get<T>(path: string, success: (T) => void, fail: (error) => void = () => {
         },
         token: boolean = false): void {
    const tokenStr = this.config.token;
    const httpOptions = {
      headers: new HttpHeaders({
        Authorization: tokenStr
      })
    };
    this.http.get<Result<T>>(this.config.baseUrl + path, httpOptions).pipe(catchError(async (error) => {
      fail(error.error.message);
    })).subscribe(resultData => {
      if (typeof resultData === 'object') {
        if (resultData.code === 200) {
          success(resultData.data);
        }
      }
    });
  }

  delete<T>(path: string, success: (T) => void, fail: (error) => void = () => {
         }): void {
    const tokenStr = this.config.token;
    const httpOptions = {
      headers: new HttpHeaders({
        Authorization: tokenStr
      })
    };
    this.http.delete<Result<T>>(this.config.baseUrl + path, httpOptions).pipe(catchError(async (error) => {
      fail(error.error.message);
    })).subscribe(resultData => {
      if (typeof resultData === 'object') {
        if (resultData.code === 200) {
          success(resultData.data);
        }
      }
    });
  }

  post<T>(path: string, data: any, success: (T) => void, fail: (error) => void = () => {
          },
          token: boolean = false): void {
    const tokenStr = this.config.token;
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: tokenStr
      })
    };
    this.http.post<Result<T>>(this.config.baseUrl + path, data, httpOptions).pipe(catchError(async (error) => {
      fail(error.error.message);
    })).subscribe(resultData => {

      if (typeof resultData === 'object') {
        if (resultData.code === 200) {
          success(resultData.data);
        }
      }
    });

  }
  put<T>(path: string, data: any, success: (T) => void, fail: (error) => void = () => {
          },
          token: boolean = false): void {
    const tokenStr = this.config.token;
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: tokenStr
      })
    };
    this.http.put<Result<T>>(this.config.baseUrl + path, data, httpOptions).pipe(catchError(async (error) => {
      fail(error.error.message);
    })).subscribe(resultData => {

      if (typeof resultData === 'object') {
        if (resultData.code === 200) {
          success(resultData.data);
        }
      }
    });

  }

}
