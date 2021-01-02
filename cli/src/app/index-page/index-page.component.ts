import {Component, OnInit} from '@angular/core';
import {TitleService} from '../../services/title.service';
import {ConfigService} from '../../services/config.service';

@Component({
  selector: 'app-index-page',
  templateUrl: './index-page.component.html',
  styleUrls: ['./index-page.component.scss']
})
export class IndexPageComponent implements OnInit {

  constructor(
    private titleService: TitleService,
    private configService: ConfigService
  ) {
  }

  ngOnInit(): void {
    this.titleService.setTitle('欢迎');
  }

  isLogin(): boolean {
    return this.configService.isLogin();
  }
}
