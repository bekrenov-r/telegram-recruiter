import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { HeaderComponent } from './header/header.component';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import {MatToolbar} from "@angular/material/toolbar";
import {MatCard, MatCardActions, MatCardContent, MatCardHeader} from "@angular/material/card";
import {MatStep, MatStepper} from "@angular/material/stepper";
import {MatFormField, MatInput, MatInputModule, MatLabel} from "@angular/material/input";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatButton, MatButtonModule} from "@angular/material/button";
import { AccountComponent } from './account/account.component';
import {MatAutocomplete, MatAutocompleteTrigger, MatOption} from "@angular/material/autocomplete";
import {MatChipGrid, MatChipInput, MatChipRemove, MatChipRow} from "@angular/material/chips";
import {MatIcon} from "@angular/material/icon";
import {MatCheckbox} from "@angular/material/checkbox";
import {MatExpansionPanel, MatExpansionPanelDescription, MatExpansionPanelHeader} from "@angular/material/expansion";
import {HttpClientModule} from "@angular/common/http";
import {MatDrawerContainer} from "@angular/material/sidenav";
import {MatSidenavModule} from '@angular/material/sidenav'
import {MatDialogModule} from "@angular/material/dialog";
import {MatSelect, MatSelectTrigger} from "@angular/material/select";
import { OfferComponent } from './dashboard/offer/offer.component';
import { NewOfferComponent } from './dashboard/new-offer/new-offer.component';
import {MatRadioButton, MatRadioGroup} from "@angular/material/radio";
import { AcceptOfferComponent } from './accept-offer/accept-offer.component';

@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    HeaderComponent,
    AccountComponent,
    OfferComponent,
    NewOfferComponent,
    AcceptOfferComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatToolbar,
    MatCard,
    MatStepper,
    MatStep,
    MatInput,
    MatFormField,
    FormsModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButton,
    MatCardHeader,
    MatCardContent,
    MatAutocomplete,
    MatAutocompleteTrigger,
    MatOption,
    MatChipGrid,
    MatChipInput,
    MatChipRow,
    MatIcon,
    MatChipRemove,
    MatCheckbox,
    MatExpansionPanel,
    MatExpansionPanelHeader,
    MatExpansionPanelDescription,
    MatLabel,
    HttpClientModule,
    MatSelect,
    MatSelectTrigger,
    MatButtonModule,
    MatCardActions,
    MatRadioButton,
    MatRadioGroup
  ],
  providers: [
    provideAnimationsAsync()
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
