import {Component, inject, OnInit, ViewChild} from '@angular/core';
import {Offer} from "./offer.model";
import {TelegramService} from "../telegram.service";
import {MatDrawer} from "@angular/material/sidenav";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {HttpService} from "../http.service";
import {AccountService} from "../account/account.service";
import {Filter} from "./filter.model";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.scss'
})
export class DashboardComponent implements OnInit{
  offers: Offer[];
  @ViewChild('drawer') drawer: MatDrawer;
  tgr: any;
  tg = inject(TelegramService);
  form;
  modes: string[] = [];
  levels: string[] = []
  voivodeships: string[] = [];
  allModes: string[]
  allLevels: string[];
  allVoivodeships: string[];
  constructor(
    public httpService: HttpService,
    private accountService: AccountService
  ) {}
  ngOnInit() {
    this.allVoivodeships = this.accountService.voivodeships;
    this.allLevels = this.accountService.levels;
    this.allModes = this.accountService.modes;
    this.form = new FormGroup({
      voivodeship: new FormControl('', Validators.required),
      mode: new FormControl('', Validators.required),
      level: new FormControl('', Validators.required),
    })
    this.httpService.getOffers({}).subscribe(
      offers => this.offers = offers);
    this.offers = this.httpService.offers;
    // this.tg.MainButton.show();
  }

  onFilter() {
    let filter: Filter = {...this.form.value};
    this.httpService.getOffers(filter).subscribe(
    offers => this.offers = offers);
  }


}
