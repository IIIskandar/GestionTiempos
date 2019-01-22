import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdminCrearComponent } from './admin-crear.component';
import { AdminCrearRoutingModule } from './admin-crear-routing.module';

@NgModule({
  declarations: [AdminCrearComponent],
  imports: [
    CommonModule,
    AdminCrearRoutingModule
  ]
})
export class AdminCrearModule { }
