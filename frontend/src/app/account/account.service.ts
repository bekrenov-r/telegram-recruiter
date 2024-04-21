import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AccountService {
  levels: string[] = ['INTERN', 'JUNIOR', 'REGULAR', 'SENIOR', 'LEAD'];
  positions: {name: string, text: string}[] = [
    {name: 'FRONTEND_DEV', text: 'FRONTEND'},
    {name: 'BACKEND_DEV', text: 'BACKEND'},
    {name: 'FULLSTACK_DEV', text: 'FULLSTACK'},
    {name: 'PROJECT_MANAGER', text: 'PROJECT MANAGER'},
    {name: 'DEVOPS', text: 'DEVOPS'},
    {name: 'TESTER', text: 'TESTER'},
    {name: 'TEAM_LEAD', text: 'TEAM LEAD'},
    {name: 'TECH_LEAD', text: 'TECH LEAD'},
  ];
  technologies: {name: string, text: string}[] = [
    {name: 'JAVA_SCRIPT', text: 'JAVASCRIPT'},
    {name: 'TYPE_SCRIPT', text: 'TYPESCRIPT'},
    {name: 'JAVA', text: 'JAVA'},
    {name: 'C_PLUS_PLUS', text: 'C++'},
    {name: 'C_SHARP', text: 'C#'},
    {name: 'ANGULAR', text: 'ANGULAR'},
    {name: 'REACT', text: 'REACT'},
    {name: 'EMBEDDED', text: 'EMBEDDED'},
    {name: 'RUBY', text: 'RUBY'},
    {name: 'VUE', text: 'VUE'}
  ]
  modes: string[] = [
    'REMOTE', 'HYBRID', 'OFFICE'
  ]
  voivodeships: string[] = ['Dolnośląskie', 'Kujawsko-pomorskie', 'Lubelskie', 'Lubuskie', 'Łódzkie', 'Małopolskie', 'Mazowieckie', 'Opolskie', 'Podkarpackie', 'Podlaskie', 'Pomorskie', 'Śląskie', 'Świętokrzyskie', 'Warmińsko-mazurskie', 'Wielkopolskie', 'Zachodniopomorskie'];
  constructor() { }
}
