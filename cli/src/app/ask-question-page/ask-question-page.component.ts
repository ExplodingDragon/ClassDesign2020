import {Component, OnInit} from '@angular/core';
import {HttpService} from '../../services/http.service';
import {ActivatedRoute, Router} from '@angular/router';
import {TitleService} from '../../services/title.service';
import {ConfigService} from '../../services/config.service';

@Component({
  selector: 'app-ask-question-page',
  templateUrl: './ask-question-page.component.html',
  styleUrls: ['./ask-question-page.component.scss']
})
export class AskQuestionPageComponent implements OnInit {

  constructor(private httpService: HttpService,
              private route: ActivatedRoute,
              private route2: Router,
              private title: TitleService,
              private configService: ConfigService) {
  }

  input = {
    id: 0,
    title: 'string',
    createUser: 'string',
    allowGuest: true,
    askSize: 0,
    contents: [
      {
        id: 0,
        optionType: 'ONE_SELECT',
        title: 'string',
        options: [
          {
            id: 0,
            content: 'string',
            select: false
          }
        ],
        input: ''
      }
    ]
  };
  select = '';


  ngOnInit(): void {
    this.route.params.subscribe((data) => {
      const fun = (res) => {
        this.input = res;
        this.title.setTitle('填写' + res.title);
      };
      this.httpService.get('/pages/questing/' + data.id, (isGuest) => {
        if (!isGuest.guest && !this.configService.isLogin()) {
          alert('此投票登录可见！');
          this.route2.navigate(['/login']);
          return;
        }
        this.httpService.get('/pages/questing/' + data.id + '/details', fun);
      });
    });
  }

  postData() {
    const output = {
      questionId: this.input.id,
      answers: []
    };
    for (let i = 0; i < this.input.contents.length; i++) {
      const content = this.input.contents[i];
      if (content.optionType === 'ONE_SELECT' || content.optionType === 'MANY_SELECT') {
        const items = {
          id: content.id,
          data: []
        };
        for (const option of content.options) {
          if (option.select) {
            items.data.push(option.id.toString());
          }
        }
        if (items.data.length === 0) {
          alert('你有必填选项未完成！');
          return;
        }
        output.answers.push(items);
      } else {
        if (content.input === '') {
          alert('你有必填项目未完成！');
          return;
        }
        output.answers.push({
          id: content.id,
          data: [content.input]
        });
      }
    }
    let url = '/api/ask';
    if (this.input.allowGuest) {
      url = '/pages/questing/ask';
    }
    console.log(output);
    this.httpService.post(url, output, (res) => {
      console.log(res);
      if (res.status) {
        alert('投票完成！');
        this.route2.navigate(['/']);
      }
      return;
    });
  }
}
