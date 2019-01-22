import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdminCategoryComponent } from './admin-category.component';
import { AdminCategoryRoutingModule } from './admin-category-routing.module';


@NgModule({
  declarations: [AdminCategoryComponent],
  imports: [
    CommonModule,
    AdminCategoryRoutingModule
  ]
})
export class AdminCategoryModule { }
