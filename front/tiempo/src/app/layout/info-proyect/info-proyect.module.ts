import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { InfoProyectComponent } from './info-proyect.component';
import { InfoProyectRoutingModule } from './info-proyect-routing.module';
import { FlexLayoutModule } from '@angular/flex-layout';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {
  MatAutocompleteModule, MatCardModule, MatCheckboxModule, MatDatepickerModule,
  MatFormFieldModule, MatInputModule, MatNativeDateModule, MatRadioModule, MatSelectModule,
  MatSliderModule, MatSlideToggleModule, MatButtonModule, MatIconModule
} from '@angular/material';

@NgModule({
  declarations: [InfoProyectComponent],
  imports: [
    CommonModule,
    InfoProyectRoutingModule,
    MatButtonModule,
    FlexLayoutModule.withConfig({addFlexToParent: false}),
    MatCardModule,
    MatSelectModule,
    FormsModule,
    ReactiveFormsModule,
    MatIconModule,
    MatDatepickerModule,
    MatNativeDateModule
  ]
})
export class InfoProyectModule { }
