import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminSuspensionComponent } from './adminSuspension.component';

const routes: Routes = [
    {
        path: '',
        component: AdminSuspensionComponent
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class AdminSuspensionRoutingModule {}
