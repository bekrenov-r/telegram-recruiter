import {AfterViewChecked, Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {HttpService} from "../http.service";
import {MatChipInputEvent} from "@angular/material/chips";
import {COMMA, ENTER} from "@angular/cdk/keycodes";
import {MatAutocompleteSelectedEvent} from "@angular/material/autocomplete";
import {map, Observable, startWith} from "rxjs";
import {Employee} from "../dashboard/employee.model";
import {AccountService} from "./account.service";
import {MatCheckboxChange} from "@angular/material/checkbox";
import {Hr} from "../dashboard/hr.model";
import {TelegramService} from "../telegram.service";
import {Router} from "@angular/router";


@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrl: './account.component.scss'
})
export class AccountComponent implements OnInit, AfterViewChecked{
  separatorKeysCodes: number[] = [ENTER, COMMA];
  form: FormGroup;
  employeeForm: FormGroup;
  hrForm: FormGroup;
  technologies: string[] = [];
  allLevels: string[];
  levels: string[] = [];
  positions: {name: string, text: string}[];
  allTechnologies: {name: string, text: string}[];
  allModes: string[];
  modes: string[] = [];
  voivodeships: string[];

  @ViewChild('techInput') techInput: ElementRef;
  choice: string = 'pracownik';


  constructor(
    private httpService: HttpService,
    private accountService: AccountService,
    private tg: TelegramService,
    private router: Router
  ) {}
  ngOnInit() {
    this.choice = this.httpService.jwtDecoded['role'] || 'pracownik';
    // window['Telegram']['webApp']['MainButton'].text("Zaloguj się jako "+ this.choice);
    this.allLevels = this.accountService.levels;
    this.positions = this.accountService.positions;
    this.allModes = this.accountService.modes;
    this.voivodeships = this.accountService.voivodeships;
    this.allTechnologies = this.accountService.technologies;

    this.employeeForm = new FormGroup({
      firstName: new FormControl('', Validators.required),
      surname: new FormControl('', Validators.required),
      technologies: new FormControl('', Validators.required),
      voivodeship: new FormControl('', Validators.required)
    });

    this.hrForm = new FormGroup({
      firstName: new FormControl('', Validators.required),
      surname: new FormControl('', Validators.required),
      companyId: new FormControl('', Validators.required)
    })

    this.form = this.employeeForm;
    this.employeeForm.patchValue({
      firstName: this.httpService.jwtDecoded.firstName
    })
    if(this.httpService.jwtDecoded.lastName) {
      this.employeeForm.patchValue({
        lastName: this.httpService.jwtDecoded.lastName
      })
    }

    this.hrForm.patchValue({
      firstName: this.httpService.jwtDecoded.firstName
    })
    if(this.httpService.jwtDecoded.lastName) {
      this.hrForm.patchValue({
        lastName: this.httpService.jwtDecoded.lastName
      })
    }
    // this.filteredTechnologies = this.form.get('technologies')!.valueChanges.pipe(
    //   startWith(null),
    //   map((option: string | null) => (option ? this._filter(option) : this.allTechnologies.slice())),
    // );
  }

  ngAfterViewChecked() {
    this.form = this.choice === 'pracownik' ? this.employeeForm : this.hrForm;
  }

  selected(event: MatAutocompleteSelectedEvent): void {
    this.technologies.push(event.option.viewValue);
    this.techInput.nativeElement.value = '';
    this.form.controls['technologies'].setValue(null);
  }

  remove(option: string): void {
    const index = this.technologies.indexOf(option);

    if (index >= 0) {
      this.technologies.splice(index, 1);
    }
  }

  addMode(e: MatCheckboxChange, option: string) {
    e.checked ? this.modes.push(option) : this.modes.filter(mode => mode === option);
  }
  addLevel(e: MatCheckboxChange, option: string) {
    console.log(e.checked, e.checked ? this.levels.push(option) : this.levels.filter(mode => mode === option))
    e.checked ? this.levels.push(option) : this.levels.filter(mode => mode === option);
  }

  onChangeChoice() {
    if(this.choice === 'pracownik') {
      this.choice = 'rekruter';
      this.form = this.hrForm;
    } else {
      this.choice = 'pracownik';
      this.form = this.employeeForm;
    }
  }

  submitForm() {
    // let user = this.form.value;
    if(this.form.invalid) return;
    if(this.choice === 'pracownik') {
      let employee: Employee = {
        preferredLocationVoivodeship: this.form.value.voivodeship,
        preferredTechnologies: this.technologies,
        preferredLevels: this.levels,
        preferredWorkModes: this.modes.map(mode => mode)
      };

      this.httpService.registrateUser(employee);
    } else {
      let hr: Hr = {
        companyId: this.form.value.companyId
      };
      this.httpService.registrateRecruiter(hr).subscribe(jwt => {
        this.httpService.token = jwt;
        this.router.navigate(['/']);
      });
    }

    if(this.choice === 'pracownik') {
    } else {
      this.httpService.registrateRecruiter();
    }
  }

}
