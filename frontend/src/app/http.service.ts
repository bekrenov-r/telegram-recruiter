import { Injectable } from '@angular/core';
import {Employee} from "./dashboard/employee.model";
import {environment} from "./dev.env";
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {Hr} from "./dashboard/hr.model";
import {Filter} from "./dashboard/filter.model";
import {tap} from "rxjs";
import {Offer} from "./dashboard/offer.model";

import * as jwt from 'jwt-decode';
import {T} from "@angular/cdk/keycodes";
import {JwtDecodeOptions} from "jwt-decode";
export interface JwtPayload extends jwt.JwtPayload {
  roles: string;
}


@Injectable({
  providedIn: 'root'
})
export class HttpService {
  offers: Offer[] = [
    {
      id: '1',
      title: 'Title',
      stack: ['Docker', 'Kubernetes'],
      description: 'description',
      city: 'Warszawa',
      firm: 'Firma',
      mode: 'Stacjonarnie',
      date: '4/20/2024'
    },
    {
      id: '1',
      title: 'Title',
      stack: ['Docker', 'Kubernetes'],
      description: 'description',
      city: 'Warszawa',
      firm: 'Firma',
      mode: 'Stacjonarnie',
      date: '4/20/2024'
    },
    {
      id: '1',
      title: 'Title',
      stack: ['Docker', 'Kubernetes'],
      description: 'description',
      city: 'Warszawa',
      firm: 'Firma',
      mode: 'Stacjonarnie',
      date: '4/20/2024'
    },
    {
      id: '1',
      title: 'Title',
      stack: ['Docker', 'Kubernetes'],
      description: 'description',
      city: 'Warszawa',
      firm: 'Firma',
      mode: 'Stacjonarnie',
      date: '4/20/2024'
    },
  ];
  token: string;
  constructor(
    private http: HttpClient
  ) { }

  registrateUser(user: Employee) {
    console.log(user);
    console.log(this.token);
    let headers = new HttpHeaders().append('Authorization', this.token);
    console.log(headers)
    this.http.post(`${environment.backendUrl}/candidates`, user, {headers}).subscribe(jwt => {
      let jwtDecoded = jwtDecode(jwt);
      console.log(jwtDecoded);
    })
  }

  registrateRecruiter(recruiter?: Hr) {
    console.log(recruiter)
    this.http.post(`${environment.backendUrl}/recruiters`, recruiter).subscribe(jwt => {

    })
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
      // this.offers = offers;
    }))
  }



}
export declare function jwtDecode<T = JwtPayload>(token: Object, options?: JwtDecodeOptions): T;
