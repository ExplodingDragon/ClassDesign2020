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
}
