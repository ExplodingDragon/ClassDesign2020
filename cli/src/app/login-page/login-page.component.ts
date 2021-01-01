import {Component, OnInit} from '@angular/core';
import {TitleService} from '../../services/title.service';
import {FormBuilder, Validators} from '@angular/forms';
import {UrlService} from '../../services/url.service';
import {HttpClient} from '@angular/common/http';
import {catchError} from 'rxjs/operators';
import {Result} from '../../models/global.model';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.scss']
})
export class LoginPageComponent implements OnInit {
  constructor(private titleService: TitleService,
              private fb: FormBuilder,
              private http: HttpClient,
              private route: Router,
              private urlService: UrlService) {

  }

  success = true;

  loginForm = this.fb.group({
      email: ['', [Validators.email, Validators.required]],
      password: ['', Validators.required]
    }
  );


  ngOnInit(): void {
    this.titleService.setTitle('登录');

  }


  onSubmit(): void {
    this.http.post<Result<string>>(this.urlService.loginUrl, this.loginForm.value).pipe(
      catchError(async (err) => {
          this.success = false;
        }
      )).subscribe(data => {
      console.log('{}', data);
      if (data) {
        this.route.navigate(['/index']);
      }

    });
  }
}
