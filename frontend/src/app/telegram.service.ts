import {Inject, Injectable} from '@angular/core';
import {DOCUMENT} from "@angular/common";

// interface TgButton {
//   show(): void
//   hide(): void
//   setText(text: string): void
// }

@Injectable({
  providedIn: 'root'
})
export class TelegramService {
  private window;
  tg: any;
  constructor() {}

  getTg() {
    return window['Telegram']['WebApp'];
  }
}
