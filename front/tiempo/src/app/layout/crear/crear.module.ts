import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CrearComponent } from './crear.component';
import { FlexLayoutModule } from '@angular/flex-layout';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CategoriasComponent } from './categorias/categorias.component';
import {
  MatAutocompleteModule, MatCardModule, MatCheckboxModule, MatDatepickerModule,
  MatFormFieldModule, MatInputModule, MatNativeDateModule, MatRadioModule, MatSelectModule,
  MatSliderModule, MatSlideToggleModule, MatButtonModule, MatIconModule
} from '@angular/material';
import { CrearRoutingModule } from './crear-routing.module';
import { UsuarioformComponent } from './usuarioform/usuarioform.component';
import { NgSelectModule } from '@ng-select/ng-select';

@NgModule({
  declarations: [CrearComponent, CategoriasComponent, UsuarioformComponent],
  imports: [
    CommonModule,
    MatButtonModule,
    FlexLayoutModule,
    CrearRoutingModule,
    MatCardModule,
    MatSelectModule,
    FormsModule,
    ReactiveFormsModule,
    MatIconModule,
    MatAutocompleteModule,
    ReactiveFormsModule,
    NgSelectModule
  ]
})
export class CrearModule { }
