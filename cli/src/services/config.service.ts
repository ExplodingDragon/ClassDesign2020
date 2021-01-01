import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ConfigService {
  baseUrl = 'http://127.0.0.1:8088';

  constructor() {
  }


}
