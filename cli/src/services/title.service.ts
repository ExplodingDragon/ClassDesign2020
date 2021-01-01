import {Title} from '@angular/platform-browser';
import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TitleService {

  constructor(private title: Title) {
  }

  public setTitle(title: string): void {
    this.title.setTitle(title + ' -  问卷调查系统');
  }

  public getTitle(): string {
    return this.title.getTitle();
  }
}
