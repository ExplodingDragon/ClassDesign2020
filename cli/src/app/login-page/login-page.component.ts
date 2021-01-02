import {Component, OnInit} from '@angular/core';
import {TitleService} from '../../services/title.service';
import {FormBuilder, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {HttpService} from '../../services/http.service';
import {ConfigService} from '../../services/config.service';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.scss']
})
export class LoginPageComponent implements OnInit {

  constructor(private titleService: TitleService,
              private fb: FormBuilder,
              private route: Router,
              private httpService: HttpService,
              private configService: ConfigService) {

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
    this.httpService.post<string>('/pages/login', this.loginForm.value
      , (data) => {
        if (data) {
          alert('登录成功！' + data.name);
          this.configService.save(data);
          this.route.navigate(['/index']).then(r => console.log(data));
        }
      }, (data) => {
        alert(data);
      });
  }
}
