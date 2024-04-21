import {Company} from "./offer/company.model";

export interface Offer {
  id: string;
  description: string;
  city: string;
  company: Company;
  level: string
  name: string
  position: string
  technologies: string[]
  workMode: string
}
