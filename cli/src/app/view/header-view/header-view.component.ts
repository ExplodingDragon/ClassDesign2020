import {Component, Input, OnInit} from '@angular/core';
import {faPaperclip} from '@fortawesome/free-solid-svg-icons';
import {ConfigService} from '../../../services/config.service';
import {HttpService} from '../../../services/http.service';
import {Router} from '@angular/router';
import {TitleService} from '../../../services/title.service';

@Component({
  selector: 'app-header-view',
  templateUrl: './header-view.component.html',
  styleUrls: ['./header-view.component.scss']
})
export class HeaderViewComponent implements OnInit {

  constructor(
    private configService: ConfigService,
    private httpService: HttpService,
    private titleService: TitleService,
    private route: Router
  ) {
  }

  paperclip = faPaperclip;

  @Input() showMenu = false;
  @Input() menuIndex = '0';

  menus = [
    {
      title: '问卷统计', href: '/panel', admin: false
    },
    {
      title: '问卷管理', href: '/', admin: false
    },
    {
      title: '模板管理', href: '/', admin: false
    },
    {
      title: '用户管理', href: '/users', admin: true
    }
  ];

  selectIndex(): number {
    return parseInt(this.menuIndex, 10);
  }

  showMen(): boolean {
    return this.showMenu;
  }

  ngOnInit(): void {
    this.titleService.setTitle(
      this.menus[this.selectIndex()].title);
  }

  noLogin(): boolean {
    return !this.configService.isLogin();
  }

  name(): string {
    return this.configService.userName;
  }

  logout(): void {
    this.httpService.get<string>('/pages/logout', (data) => {
      alert('注销成功！');
      this.route.navigate(['/index']).then(r => console.log(data));
      this.configService.logout();

    }, () => {
      this.configService.logout();

    }, true);
  }

  notAdmin(): boolean {
    return this.configService.admin;
  }
}
