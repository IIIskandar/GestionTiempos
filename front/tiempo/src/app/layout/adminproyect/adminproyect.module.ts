import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdminproyectComponent } from './adminproyect.component';
import { AdminproyectRoutingModule } from './adminproyect-routing.module';
import {
  MatAutocompleteModule, MatCardModule, MatCheckboxModule, MatDatepickerModule,
  MatFormFieldModule, MatInputModule, MatNativeDateModule, MatRadioModule, MatSelectModule,
  MatSliderModule, MatSlideToggleModule, MatButtonModule
} from '@angular/material';

@NgModule({
  declarations: [AdminproyectComponent],
  imports: [
    CommonModule,
    MatButtonModule,
    AdminproyectRoutingModule
  ]
})
export class AdminproyectModule { }
