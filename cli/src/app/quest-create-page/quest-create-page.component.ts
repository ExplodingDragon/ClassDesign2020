import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-quest-create-page',
  templateUrl: './quest-create-page.component.html',
  styleUrls: ['./quest-create-page.component.scss']
})
export class QuestCreatePageComponent implements OnInit {

  constructor() {
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

  save() {
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
        }
      }
    }
    console.log(this.input);
  }
}
