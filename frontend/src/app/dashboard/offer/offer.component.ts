import {Component, OnInit} from '@angular/core';
import {Offer} from "../offer.model";
import {HttpService} from "../../http.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-offer',
  templateUrl: './offer.component.html',
  styleUrl: './offer.component.scss'
})
export class OfferComponent implements OnInit{
  offer: Offer;

  constructor(
    private http: HttpService,
    private route: ActivatedRoute
  ) {}
  ngOnInit() {
    let offerId = this.route.snapshot.params['id'];
    this.offer = this.http.offers.find(offer => offer.id === offerId)!;
  }
}
