import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {DashboardComponent} from "./dashboard/dashboard.component";
import {AccountComponent} from "./account/account.component";
import {OfferComponent} from "./dashboard/offer/offer.component";

const routes: Routes = [
  {path: "", component: DashboardComponent, pathMatch: "full"},
  {path: "account", component: AccountComponent},
  {path: `offers/:id`, component: OfferComponent},

  // {path: "**", redirectTo: ""}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
