import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {HttpService} from '../../services/http.service';

// noinspection DuplicatedCode
@Component({
  selector: 'app-question-details-page',
  templateUrl: './question-details-page.component.html',
  styleUrls: ['./question-details-page.component.scss']
})
export class QuestionDetailsPageComponent implements OnInit {

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
            content: 'data',
            selectSize: 0
          }
        ]
      }
    ]
  };
  id = 0;
  type = 'read';

  disable(): boolean {
    return !(this.type === 'write');
  }

  constructor(private route: ActivatedRoute, private route2: Router, private httpService: HttpService) {
    route.params.subscribe((res) => {
      this.id = res.id;
      this.type = res.type;
    });
  }

  ngOnInit(): void {
    this.httpService.get((this.disable() ? '/api/ask/' : '/api/questions/') + this.id, (data) => {
      this.input = data;
    });
  }

  addQuestion(): void {
    this.input.contents.push({
      id: 0,
      optionType: 'ONE_SELECT',
      title: 'string',
      options: [
        {
          id: 0,
          content: '题号',
          selectSize: 0
        }
      ]
    });
  }

  addQuestionItem(index: number): void {
    this.input.contents[index].options.push({
      id: 0,
      content: '选项',
      selectSize: 0
    });
  }

  deleteQuestion(i: number): void {
    this.input.contents.splice(i, 1);
  }

  deleteQuestionItem(i: number, j: number): void {
    this.input.contents[i].options.splice(j, 1);
  }

  save(): void {
    if (this.input.contents.length === 0) {
      alert('问卷为空！');
      return;
    }
    for (let i = 0; i < this.input.contents.length; i++) {
      const d = this.input.contents[i];
      if (d.optionType !== 'INPUT') {
        if (d.options.length === 0) {
          alert('问卷题目' + (i + 1) + '中，没有选项！');
          return;
        } else if (d.options.length === 1) {
          alert('问卷题目' + (i + 1) + '中，只有一项可选择');
          return;
        }
      }
    }

    this.httpService.put('/api/questions/' + this.id, this.input, (data) => {
      alert('修改成功！');
      this.route2.navigate(['/quest']).then(() => console.log(data));
    });
  }

  desc(n1: number, n2: number): string {
    console.log(n1);
    if (n2 > 0 && n1 > 0) {
      return parseInt(String((n1 * 100 / n2)), 10).toString();
    } else {
      return '0';
    }
  }
}
