import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { EditProyectComponent } from './edit-proyect.component';
import { EditProyectRoutingModule } from './edit-proyect-routing.module';

@NgModule({
  declarations: [EditProyectComponent],
  imports: [
    CommonModule,
    EditProyectRoutingModule
  ]
})
export class EditProyectModule { }
