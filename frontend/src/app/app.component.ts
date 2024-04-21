import {Component, OnInit} from '@angular/core';
import {TelegramService} from "./telegram.service";
import {ActivatedRoute} from "@angular/router";
import {HttpService} from "./http.service";
const SCRIPT_PATH = 'https://telegram.org/js/telegram-web-app.js';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent implements OnInit{
  title = 'recruIT';

  constructor(private tg: TelegramService,
              private route: ActivatedRoute,
              private http: HttpService) { }
  // telegram = inject(TelegramService);

  ngOnInit() {
    console.log(this.tg.getTg());
    this.route.queryParams.subscribe(params => {
      if(params['token'])this.http.token = 'Bearer ' + params['token'];
      console.log(this.http.token)
    });
  }
}
