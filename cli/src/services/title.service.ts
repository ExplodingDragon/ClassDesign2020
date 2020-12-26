import { Title } from '@angular/platform-browser';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TitleService {

  constructor(private title: Title) { }

  public setTitle(title: string): void {
    this.title.setTitle(title);
  }

  public getTitle(): string {
    return this.title.getTitle();
  }
}
