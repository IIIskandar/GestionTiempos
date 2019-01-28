import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdminproyectComponent } from './adminproyect.component';
import { AdminproyectRoutingModule } from './adminproyect-routing.module';
import { FlexLayoutModule } from '@angular/flex-layout';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {
  MatAutocompleteModule, MatCardModule, MatCheckboxModule, MatDatepickerModule,
  MatFormFieldModule, MatInputModule, MatNativeDateModule, MatRadioModule, MatSelectModule,
  MatSliderModule, MatSlideToggleModule, MatButtonModule, MatIcon, MatIconModule
} from '@angular/material';

@NgModule({
  declarations: [AdminproyectComponent],
  imports: [
    CommonModule,
    MatButtonModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatFormFieldModule,
    MatIconModule,
    MatCardModule,
    AdminproyectRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    FlexLayoutModule.withConfig({addFlexToParent: false})
  ]
})
export class AdminproyectModule { }
