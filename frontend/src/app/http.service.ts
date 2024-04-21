import { Injectable } from '@angular/core';
import {Employee} from "./dashboard/employee.model";
import {environment} from "./dev.env";
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {Hr} from "./dashboard/hr.model";
import {Filter} from "./dashboard/filter.model";
import {tap} from "rxjs";
import {Offer} from "./dashboard/offer.model";

import {jwtDecode, JwtPayload} from 'jwt-decode';
import {UserJwtPayload} from "./http/jwt.model";
import {NewOffer} from "./dashboard/new-offer/new-offer.model";
import {AcceptOffer} from "./accept-offer/accept-offer.model";


@Injectable({
  providedIn: 'root'
})
export class HttpService {
  offers: Offer[] = [];
  private _token: string;
  jwtDecoded: UserJwtPayload;
  constructor(
    private http: HttpClient
  ) { }

  get token() {
    return this._token;
  }
  set token(token: string) {
    this._token = token;
    let jwtDecoded: UserJwtPayload = jwtDecode(token);
    this.jwtDecoded = jwtDecoded;
    console.log(jwtDecoded);
  }

  registrateUser(user: Employee) {
    console.log(user);
    console.log(this.token);
    let headers = new HttpHeaders().append('Authorization', this.token);
    console.log(headers)
    this.http.post<string>(`${environment.backendUrl}/candidates`, user, {headers}).subscribe(jwt => {
      this.token = jwt;
    })
  }

  registrateRecruiter(recruiter?: Hr) {
    console.log(recruiter)
    return this.http.post<string>(`${environment.backendUrl}/recruiters`, recruiter).pipe(tap(jwt => {
      this.token = jwt;
      console.log(this.jwtDecoded);
    }))
  }

  getOffers(filter: Filter) {
    console.log(filter)
    let params = new HttpParams();
    Object.entries(filter).forEach(([k,v]) => {
      params = params.append(k, v);
    })
    let headers = new HttpHeaders().append('Authorization', this.token);
    console.log(params)
    return this.http.get<Offer[]>(`${environment.backendUrl}/offers`, {params: params, headers: headers}).pipe(tap(offers => {
      this.offers = offers;
    }))
  }

  createOffer(offer: NewOffer) {
    let headers = new HttpHeaders().append('Authorization', this.token);

    return this.http.post(`${environment.backendUrl}/offers`, offer, {headers})
  }

  acceptOffer(acceptOffer: AcceptOffer) {
    let headers = new HttpHeaders().append('Authorization', this.token);
    return this.http.post(`${environment.backendUrl}/applications`, acceptOffer, {headers})
  }
  attachCVToApplication(cv: File, offerId: string) {
    let formData = new FormData().append('', cv);
    let headers = new HttpHeaders().append('Authorization', this.token);
    return this.http.patch(`${environment.backendUrl}/applications/${offerId}/cv`, formData, {headers})
  }




}
// export declare function jwtDecode<T = JwtPayload>(token: Object, options?: JwtDecodeOptions): T;
