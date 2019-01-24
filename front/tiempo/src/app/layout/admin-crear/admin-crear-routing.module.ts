import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminCrearComponent } from './admin-crear.component';

const routes: Routes = [
    {
        path: '',
        component: AdminCrearComponent
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class AdminCrearRoutingModule {}
