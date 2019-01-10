import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TareaComponent } from './tarea.component';
import { TareaRoutingModule } from './tarea-routing.module';
import {
  MatAutocompleteModule, MatCardModule, MatCheckboxModule, MatDatepickerModule,
  MatFormFieldModule, MatInputModule, MatNativeDateModule, MatRadioModule, MatSelectModule,
  MatSliderModule, MatSlideToggleModule, MatButtonModule
} from '@angular/material';

@NgModule({
  declarations: [TareaComponent],
  imports: [
    CommonModule,
    TareaRoutingModule,
    MatButtonModule
  ]
})
export class TareaModule { }
