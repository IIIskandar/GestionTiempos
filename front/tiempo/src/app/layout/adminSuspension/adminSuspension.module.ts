import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdminSuspensionComponent } from './adminSuspension.component';
import { AdminSuspensionRoutingModule } from './adminSuspension-routing.module';

@NgModule({
  declarations: [AdminSuspensionComponent],
  imports: [
    CommonModule,
    AdminSuspensionRoutingModule
  ]
})
export class AdminSuspensionModule { }
