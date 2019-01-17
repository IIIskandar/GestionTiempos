import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { StatComponent } from './stat.component';
import { MatCardModule } from '@angular/material';
import { MatGridListModule, MatIconModule } from '@angular/material';
import { RouterModule } from '@angular/router';
@NgModule({
    imports: [CommonModule, MatCardModule, MatGridListModule, MatIconModule, RouterModule],
    declarations: [StatComponent],
    exports: [StatComponent]
})
export class StatModule {}
