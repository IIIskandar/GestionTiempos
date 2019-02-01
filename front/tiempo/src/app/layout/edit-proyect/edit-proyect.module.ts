import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { EditProyectComponent } from './edit-proyect.component';
import { EditProyectRoutingModule } from './edit-proyect-routing.module';
import { FlexLayoutModule } from '@angular/flex-layout';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { UsuarioformEditComponent } from './usuarioformEdit/usuarioform.component';
import {
  MatAutocompleteModule, MatCardModule, MatCheckboxModule, MatDatepickerModule,
  MatFormFieldModule, MatInputModule, MatNativeDateModule, MatRadioModule, MatSelectModule,
  MatSliderModule, MatSlideToggleModule, MatButtonModule, MatIconModule
} from '@angular/material';
import { CategoriasEditComponent } from './categoriasEdit/categorias.component';

@NgModule({
  declarations: [EditProyectComponent, CategoriasEditComponent, UsuarioformEditComponent],
  imports: [
    CommonModule,
    EditProyectRoutingModule,
    MatButtonModule,
    FlexLayoutModule,
    MatCardModule,
    MatSelectModule,
    FormsModule,
    ReactiveFormsModule,
    MatIconModule,
    MatAutocompleteModule,
    ReactiveFormsModule
  ]
})
export class EditProyectModule { }
