import {Component, OnInit} from '@angular/core';
import {HttpService} from '../../services/http.service';

@Component({
  selector: 'app-panel-page',
  templateUrl: './panel-page.component.html',
  styleUrls: ['./panel-page.component.scss']
})
export class PanelPageComponent implements OnInit {
  info = {
    registerTime: '',
    createSize: 0,
    askYourQuestSize: 0
  };

  constructor(private httpService: HttpService) {
  }

  ngOnInit(): void {
    this.httpService.get('/api/ask/info', (res) => {
      this.info = res;
      console.log(res);
    });
  }

}
