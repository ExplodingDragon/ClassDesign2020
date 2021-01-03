import {Component, OnInit} from '@angular/core';
import {HttpService} from '../../services/http.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-quest-page',
  templateUrl: './quest-page.component.html',
  styleUrls: ['./quest-page.component.scss']
})
export class QuestPageComponent implements OnInit {

  constructor(private httpService: HttpService,
              private route: Router
  ) {
  }

  input = [{
    id: 0,
    title: '无',
    createUser: '无',
    allowGuest: false,
    askSize: 0,
    contents: [
      {
        id: 0,
        optionType: 'ONE_SELECT',
        title: 'string',
        options: [
          {
            id: 0,
            content: 'string'
          }
        ]
      }
    ]
  }];

  index = 0;
  size = 0;
  splitSize = 10;

  arr(size) {
    return Array(parseInt(size.toString(), 0));
  }

  ngOnInit(): void {
    this.update();
  }

  private update() {
    this.httpService.get('/api/questions/count', (data) => {
      this.size = data.data;
      if (this.getPageSize() <= this.index) {
        this.index = this.getPageSize() - 1;
      }
      if (this.index < 0) {
        this.input = [];
        return;
      }
      this.httpService.get('/api/questions?index=' + this.index + '&size=' + this.splitSize, (data) => {
        this.input = data;
      });
    });
  }

  getPageSize() {
    if (this.size % this.splitSize !== 0) {
      return parseInt((this.size / this.splitSize).toString(), 10) + 1;
    } else {
      return this.size / this.splitSize;
    }
  }

  getPages() {
    return this.arr(this.getPageSize());
  }

  checkPage(i: number) {
    this.index = i;
    this.update();
  }

  see(id: number) {
    this.route.navigate(['/question/' + id + '/read']);
  }

  edit(id: number) {
    this.route.navigate(['/question/' + id + '/write']);
  }

  delete(id: number) {
    if (window.confirm('确认删除 ' + id + '?')) {
      this.httpService.delete('/api/questions/' + id, (data) => {
        this.update();
      });
    }
  }
}
