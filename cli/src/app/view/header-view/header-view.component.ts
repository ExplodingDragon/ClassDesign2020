import { Component, OnInit } from '@angular/core';
import { faPaperclip } from '@fortawesome/free-solid-svg-icons';
@Component({
  selector: 'app-header-view',
  templateUrl: './header-view.component.html',
  styleUrls: ['./header-view.component.scss']
})
export class HeaderViewComponent implements OnInit {
  paperclip = faPaperclip;

  constructor() { }

  ngOnInit(): void {
  }

}
