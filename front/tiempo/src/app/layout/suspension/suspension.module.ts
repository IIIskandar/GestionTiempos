import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SuspensionComponent } from './suspension.component';
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
    SuspensionRoutingModule
  ]
})
export class SuspensionModule { }
