import {Component, OnInit} from '@angular/core';
import {Offer} from "../dashboard/offer.model";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {HttpService} from "../http.service";
import {ActivatedRoute, Router} from "@angular/router";
import {AcceptOffer} from "./accept-offer.model";

@Component({
  selector: 'app-accept-offer',
  templateUrl: './accept-offer.component.html',
  styleUrl: './accept-offer.component.scss'
})
export class AcceptOfferComponent implements OnInit{
  offer: Offer;
  form;
  cv: File;

  constructor(
    private http: HttpService,
    private route: ActivatedRoute,
    private router: Router) {}
  ngOnInit() {
    this.offer = this.http.offers.find(offer => offer.id === this.route.snapshot.params['id'])!;
    this.form = new FormGroup({
      firstName: new FormControl(this.http.jwtDecoded.firstName || '', Validators.required),
      lastName: new FormControl(this.http.jwtDecoded.lastName || '', Validators.required),
      phone: new FormControl(),
      email: new FormControl('', Validators.email),
      messageToRecruiter: new FormControl()
    })
  }

  onFilePicked(event: Event) {
    // @ts-ignore
    this.cv = (event.target as HTMLInputElement).files[0];
    console.log(!!this.cv)
  }

  onSubmit() {
    let acceptOffer: AcceptOffer = {
      offerId: this.offer.id,
      ...this.form.value
    }
    console.log(acceptOffer)
    this.http.acceptOffer(acceptOffer).subscribe(res => {
      this.http.attachCVToApplication(this.cv, this.offer.id).subscribe(res => {
        this.router.navigate(['/']);
      });
    });
  }

}
