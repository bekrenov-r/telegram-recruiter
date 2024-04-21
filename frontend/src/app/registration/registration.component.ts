import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {HttpService} from "../http.service";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrl: './registration.component.scss'
})
export class RegistrationComponent implements OnInit{
  form: FormGroup;

  constructor(private httpService: HttpService) {
  }

  ngOnInit() {
    this.form = new FormGroup({
      name: new FormControl('', Validators.required),
      surname: new FormControl('', Validators.required),
      email: new FormControl('', [Validators.required, Validators.email])
    });
  }

  submitForm() {
    if(this.form.invalid) return;
    let user = {...this.form.value};
    this.httpService.registrateUser(user);
  }
}
