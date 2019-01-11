import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AccionComponent } from './accion.component';

const routes: Routes = [
    {
        path: '',
        component: AccionComponent
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class AccionRoutingModule {}
