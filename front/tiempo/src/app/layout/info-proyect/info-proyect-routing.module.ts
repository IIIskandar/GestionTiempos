import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { InfoProyectComponent } from './info-proyect.component';

const routes: Routes = [
    {
        path: '',
        component: InfoProyectComponent
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class InfoProyectRoutingModule {}
