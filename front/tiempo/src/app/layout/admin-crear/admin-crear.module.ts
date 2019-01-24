import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdminCrearComponent } from './admin-crear.component';
import { AdminCrearRoutingModule } from './admin-crear-routing.module';
import { FlexLayoutModule } from '@angular/flex-layout';
import {
  MatAutocompleteModule, MatCardModule, MatCheckboxModule, MatDatepickerModule,
  MatFormFieldModule, MatInputModule, MatNativeDateModule, MatRadioModule, MatSelectModule,
  MatSliderModule, MatSlideToggleModule, MatButtonModule
} from '@angular/material';

@NgModule({
  declarations: [AdminCrearComponent],
  imports: [
    CommonModule,
    AdminCrearRoutingModule,
    MatButtonModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatFormFieldModule,
    MatCardModule,
    FlexLayoutModule.withConfig({addFlexToParent: false})
  ]
})
export class AdminCrearModule { }
