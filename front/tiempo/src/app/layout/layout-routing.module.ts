import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { LayoutComponent } from './layout.component';

const routes: Routes = [
    {
        path: '',
        component: LayoutComponent,
        children: [
            {
                path: '',
                redirectTo: 'dashboard/proyectos'
            },
            {
                path: 'dashboard/:info',
                loadChildren: './dashboard/dashboard.module#DashboardModule'
            },
            {
                path: 'proyectos',
                loadChildren: './forms/forms.module#FormsModule'
            },
            {
                path: 'tareas/:id/:nombre',
                loadChildren: './tarea/tarea.module#TareaModule'
            },
            {
                path: 'suspension',
                loadChildren: './suspension/suspension.module#SuspensionModule'
            },
            {
                path: 'finalizar/:id/:tarea',
                loadChildren: './finalizar/finalizar.module#FinalizarModule'
            },
            {
                path: 'admin',
                loadChildren: './adminproyect/adminproyect.module#AdminproyectModule'
            },
            {
                path: 'crear',
                loadChildren: './crear/crear.module#CrearModule'
            }
        ]
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class LayoutRoutingModule {}
