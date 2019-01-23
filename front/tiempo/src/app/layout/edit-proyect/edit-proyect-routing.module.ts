import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EditProyectComponent } from './edit-proyect.component';

const routes: Routes = [
    {
        path: '',
        component: EditProyectComponent
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class EditProyectRoutingModule {}
