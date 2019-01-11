import { NgModule } from '@angular/core';
import { AccionComponent } from './accion.component';
import { CommonModule } from '@angular/common';
import {
  MatAutocompleteModule, MatCardModule, MatCheckboxModule, MatDatepickerModule,
  MatFormFieldModule, MatInputModule, MatNativeDateModule, MatRadioModule, MatSelectModule,
  MatSliderModule, MatSlideToggleModule, MatButtonModule
} from '@angular/material';
import { AccionRoutingModule } from './accion-routing.module';

@NgModule({
  declarations: [AccionComponent],
  imports: [
    CommonModule,
    AccionRoutingModule,
    MatButtonModule
  ]
})
export class AccionModule { }
