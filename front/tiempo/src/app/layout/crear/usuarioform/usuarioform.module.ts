import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UsuarioformComponent } from './usuarioform.component';
import {
  MatAutocompleteModule, MatCardModule, MatCheckboxModule, MatDatepickerModule,
  MatFormFieldModule, MatInputModule, MatNativeDateModule, MatRadioModule, MatSelectModule,
  MatSliderModule, MatSlideToggleModule, MatButtonModule, MatIconModule
} from '@angular/material';
import { FlexLayoutModule } from '@angular/flex-layout';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgSelectModule } from '@ng-select/ng-select';
import { BrowserModule } from '@angular/platform-browser';

@NgModule({
  declarations: [UsuarioformComponent],
  imports: [
    CommonModule,
    MatButtonModule,
    FlexLayoutModule,
    MatCardModule,
    MatSelectModule,
    FormsModule,
    ReactiveFormsModule,
    MatIconModule,
    MatAutocompleteModule,
    NgSelectModule,
    BrowserModule
  ]
})
export class UsuarioformModule { }
