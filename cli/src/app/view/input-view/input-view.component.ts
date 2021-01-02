import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-input-view',
  templateUrl: './input-view.component.html',
  styleUrls: ['./input-view.component.scss']
})
export class InputViewComponent implements OnInit {

  constructor() {
  }

  @Input() dialog = {
    show: false,
    content: [],
    saveHook: (data) => {
    }
  };

  ngOnInit(): void {

  }

  save() {
    this.dialog.saveHook(this.dialog.content);
    this.hide();
  }

  hide() {
    this.dialog.show = false;

  }
}
