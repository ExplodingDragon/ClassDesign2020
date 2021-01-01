import {Injectable} from '@angular/core';
import {ConfigService} from './config.service';

@Injectable({
  providedIn: 'root'
})
export class UrlService {

  constructor(private config: ConfigService) {

  }
  loginUrl = this.config.baseUrl + '/pages/login';

}
