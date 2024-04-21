import {Component, OnInit} from '@angular/core';
import {AccountService} from "../../account/account.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {MatCheckboxChange} from "@angular/material/checkbox";
import {NewOffer} from "./new-offer.model";
import {HttpService} from "../../http.service";

@Component({
  selector: 'app-new-offer',
  templateUrl: './new-offer.component.html',
  styleUrl: './new-offer.component.scss'
})
export class NewOfferComponent implements OnInit{
  form;
  voivodeships: string[];
  levels: string[];
  modes: string[];
  technologies: {name: string, text: string}[];
  allVoivodeships: string[];
  allLevels: string[];
  allModes: string[];
  allTechnologies: {name: string, text: string}[];
  constructor(
    private accountService: AccountService,
    private http: HttpService
    ) {}

  ngOnInit() {
    this.allVoivodeships = this.accountService.voivodeships;
    this.allLevels = this.accountService.levels;
    this.allModes = this.accountService.modes;
    this.allTechnologies = this.accountService.technologies;

    this.form = new FormGroup({
      name: new FormControl('', Validators.required),
      description:  new FormControl('', Validators.required),
      city:  new FormControl('', Validators.required),
      level: new FormControl('', Validators.required),
      workMode: new FormControl('', Validators.required),
      technologies:  new FormControl('', Validators.required),
      companyId:  new FormControl('', Validators.required),
    })
  }
  addMode(e: MatCheckboxChange, option: string) {
    e.checked ? this.modes.push(option) : this.modes.filter(mode => mode === option);
  }
  addLevel(e: MatCheckboxChange, option: string) {
    console.log(e.checked, e.checked ? this.levels.push(option) : this.levels.filter(mode => mode === option))
    e.checked ? this.levels.push(option) : this.levels.filter(mode => mode === option);
  }

  submitForm() {
    let newOffer: NewOffer = {
      ...this.form.value
    }
    console.log(newOffer);
    this.http.createOffer(newOffer).subscribe(() => {});
  }
}
