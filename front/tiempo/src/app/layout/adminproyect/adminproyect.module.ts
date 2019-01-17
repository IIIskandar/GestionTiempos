import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdminproyectComponent } from './adminproyect.component';
import { AdminproyectRoutingModule } from './adminproyect-routing.module';
import { FlexLayoutModule } from '@angular/flex-layout';
import {
  MatAutocompleteModule, MatCardModule, MatCheckboxModule, MatDatepickerModule,
  MatFormFieldModule, MatInputModule, MatNativeDateModule, MatRadioModule, MatSelectModule,
  MatSliderModule, MatSlideToggleModule, MatButtonModule
} from '@angular/material';

@NgModule({
  declarations: [AdminproyectComponent],
  imports: [
    CommonModule,
    MatButtonModule,
    MatCardModule,
    AdminproyectRoutingModule,
    FlexLayoutModule.withConfig({addFlexToParent: false})
  ]
})
export class AdminproyectModule { }
