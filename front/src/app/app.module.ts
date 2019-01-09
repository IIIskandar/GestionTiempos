import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ProyectosComponent } from './proyectos/proyectos.component';
import { ActividadesComponent } from './actividades/actividades.component';
import { LoginComponent } from './login/login.component';
import { TareasComponent } from './tareas/tareas.component';

@NgModule({
  declarations: [
    AppComponent,
    ProyectosComponent,
    ActividadesComponent,
    LoginComponent,
    TareasComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
