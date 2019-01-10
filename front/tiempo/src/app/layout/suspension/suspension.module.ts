import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SuspensionComponent } from './suspension.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {
  MatAutocompleteModule, MatCardModule, MatCheckboxModule, MatDatepickerModule,
  MatFormFieldModule, MatInputModule, MatNativeDateModule, MatRadioModule, MatSelectModule,
  MatSliderModule, MatSlideToggleModule, MatButtonModule
} from '@angular/material';
import { SuspensionRoutingModule } from './suspension-routing.module';

@NgModule({
  declarations: [SuspensionComponent],
  imports: [
    CommonModule,
    MatButtonModule,
    SuspensionRoutingModule,
    MatSelectModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class SuspensionModule { }
