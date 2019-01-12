import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {
  MatAutocompleteModule, MatCardModule, MatCheckboxModule, MatDatepickerModule,
  MatFormFieldModule, MatInputModule, MatNativeDateModule, MatRadioModule, MatSelectModule,
  MatSliderModule, MatSlideToggleModule, MatButtonModule
} from '@angular/material';
import { FinalizarRoutingModule } from './finalizar-routing.module';
import { FinalizarComponent } from './finalizar.component';

@NgModule({
  declarations: [FinalizarComponent],
  imports: [
    CommonModule,
    MatButtonModule,
    FinalizarRoutingModule,
    MatSelectModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class FinalizarModule { }
