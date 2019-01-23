import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdminSuspensionComponent } from './adminSuspension.component';
import { AdminSuspensionRoutingModule } from './adminSuspension-routing.module';
import { FlexLayoutModule } from '@angular/flex-layout';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {
  MatAutocompleteModule, MatCardModule, MatCheckboxModule, MatDatepickerModule,
  MatFormFieldModule, MatInputModule, MatNativeDateModule, MatRadioModule, MatSelectModule,
  MatSliderModule, MatSlideToggleModule, MatButtonModule, MatIconModule
} from '@angular/material';

@NgModule({
  declarations: [AdminSuspensionComponent],
  imports: [
    CommonModule,
    AdminSuspensionRoutingModule,
    MatButtonModule,
    FlexLayoutModule,
    MatCardModule,
    MatSelectModule,
    FormsModule,
    ReactiveFormsModule,
    MatIconModule
  ]
})
export class AdminSuspensionModule { }
