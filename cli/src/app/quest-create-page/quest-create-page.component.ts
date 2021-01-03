import {Component, OnInit} from '@angular/core';
import {HttpService} from '../../services/http.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-quest-create-page',
  templateUrl: './quest-create-page.component.html',
  styleUrls: ['./quest-create-page.component.scss']
})
export class QuestCreatePageComponent implements OnInit {


  constructor(private httpService: HttpService
    ,
              private route: Router) {
  }

  input = {
    title: '默认模板',
    allowGuest: true,
    content: [
      {
        title: '默认单选',
        optionType: 'ONE_SELECT',
        content: [
          {
            content: '选项 1'
          }
        ]
      }
    ]
  };

  ngOnInit(): void {
  }

  addQuestion(): void {
    this.input.content.push({
      title: '问题',
      optionType: 'ONE_SELECT',
      content: [
        {
          content: '选项'
        }
      ]
    });
  }

  addQuestionItem(index: number): void {
    this.input.content[index].content.push({
      content: '选项'
    });
  }

  deleteQuestion(i: number): void {
    this.input.content.splice(i, 1);
  }

  deleteQuestionItem(i: number, j: number): void {
    this.input.content[i].content.splice(j, 1);
  }

  save(): void {
    if (this.input.content.length === 0) {
      alert('问卷为空！');
      return;
    }
    for (let i = 0; i < this.input.content.length; i++) {
      const d = this.input.content[i];
      if (d.optionType !== 'INPUT') {
        if (d.content.length === 0) {
          alert('问卷题目' + (i + 1) + '中，没有选项！');
          return;
        } else if (d.content.length === 1) {
          alert('问卷题目' + (i + 1) + '中，只有一项可选择');
          return;
        }
      }
    }

    this.httpService.post('/api/questions', this.input, (data) => {
      alert('创建成功！');
      this.route.navigate(['/quest']).then(() => console.log(data));
    });
  }
}
