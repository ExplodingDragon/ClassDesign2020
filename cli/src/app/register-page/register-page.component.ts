import {Component, OnInit} from '@angular/core';
import {TitleService} from '../../services/title.service';
import {FormBuilder, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {HttpService} from '../../services/http.service';

@Component({
  selector: 'app-register-page',
  templateUrl: './register-page.component.html',
  styleUrls: ['./register-page.component.scss']
})
export class RegisterPageComponent implements OnInit {

  constructor(private titleService: TitleService,
              private fb: FormBuilder,
              private route: Router,
              private httpService: HttpService) {
  }

  success = true;

  registerForm = this.fb.group({
      email: ['', [Validators.email, Validators.required]],
      password: ['', Validators.required]
    }
  );


  ngOnInit(): void {
    this.titleService.setTitle('注册');
  }

  onSubmit(): void {
    this.httpService.post<string>('/pages/register', this.registerForm.value
      , (data) => {
        if (data) {
          alert('注册成功！请登录！');
          this.route.navigate(['/login']).then(r => console.log(data));
        }
      }, (msg) => alert(msg));
  }

}
