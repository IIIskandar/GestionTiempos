import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminproyectComponent } from './adminproyect.component';

const routes: Routes = [
    {
        path: '',
        component: AdminproyectComponent
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class AdminproyectRoutingModule {}
