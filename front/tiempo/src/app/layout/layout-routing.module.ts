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
            },
            {
                path: 'adminSuspension',
                loadChildren: './adminSuspension/adminSuspension.module#AdminSuspensionModule'
            },
            {
                path: 'adminUser',
                loadChildren: './admin-user/admin-user.module#AdminUserModule'
            },
            {
                path: 'adminConfig',
                loadChildren: './admin-crear/admin-crear.module#AdminCrearModule'
            },
            {
                path: 'editProyect/:id',
                loadChildren: './edit-proyect/edit-proyect.module#EditProyectModule'
            },
            {
                path: 'infoProyect/:id/:nombre',
                loadChildren: './info-proyect/info-proyect.module#InfoProyectModule'
            },
            {
                path: 'infoUser/:id/:nombre',
                loadChildren: './info-user/info-user.module#InfoUserModule'
            },
        ]
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class LayoutRoutingModule {}
