import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient, HttpParams } from '@angular/common/http';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http: HttpClient) { }

  private formatErrors(error: any) {
    return  throwError(error.error);
  }


  crearProyecto(proyect) {
    const head = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post(`http://localhost:8081/tiempos/v1/proyectos/`, JSON.stringify(proyect), {headers: head});
  }

  listProyect() {
    return this.http.get(`http://localhost:8081/tiempos/v1/proyectos`);
  }

  listProyectUser(cc) {
    return this.http.get(`http://localhost:8081/tiempos/v1/usuarios/` + cc + `/proyectos`);
  }

  Tareas(id) {
    return this.http.get(`http://localhost:8081/tiempos/v1/proyectos/` + id + `/tareas`);
  }

  iniciarTarea(id, cc) {
    const head = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post(`http://localhost:8081/tiempos/v1/tareas/` + id + `/registro/inicio/En%20Tarea` , {
      'madeBy': cc
    }, {headers: head});
  }

  finalizarTarea(id, status, descripcion) {
    return this.http.post(`http://localhost:8081/tiempos/v1/tareas/` + id + `/registro/fin/` + status , {
      'jobDetails': descripcion
    });
  }

  timeJobUSer(cc) {
    return this.http.get(`http://localhost:8081/tiempos/v1/usuarios/tiempo/` + cc);
  }

  timeSusUSer(cc) {
    return this.http.get(`http://localhost:8081/tiempos/v1/usuarios/tiempoSuspensiones/` + cc);
  }

  getUsers() {
    return this.http.get(`http://localhost:8081/tiempos/v1/usuarios/` );
  }

  getTiempoCategoria() {
    return this.http.get(`http://localhost:8081/tiempos/v1/tareas/tiempoCategorias` );
  }

  getTiempoSus() {
    return this.http.get(`http://localhost:8081/tiempos/v1/suspension/tiempo` );
  }

  getSus() {
    return this.http.get(`http://localhost:8081/tiempos/v1/suspension/listar` );
  }

  crearUser(user) {
    const head = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post(`http://localhost:8081/tiempos/v1/usuarios`, JSON.stringify(user), {headers: head});
  }

  crearTipoSus(suspension) {
    const head = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post(`http://localhost:8081/tiempos/v1/suspension/crear`, JSON.stringify(suspension), {headers: head});
  }

  listTareasUser(cc) {
    return this.http.get(`http://localhost:8081/tiempos/v1/usuarios/tiempoTarea/` + cc);
  }

  listUserProyect(id) {
    return this.http.get(`http://localhost:8081/tiempos/v1/proyectos/usuariosProyecto/` + id);
  }

  listTareaProyect(id) {
    return this.http.get(`http://localhost:8081/tiempos/v1/proyectos/` + id + '/tareas');
  }

  detalleProyect(cc) {
    return this.http.get(`http://localhost:8081/tiempos/v1/usuarios/tiempoProyecto/` + cc);
  }

  tiempoJobProyect(id) {
    return this.http.get(`http://localhost:8081/tiempos/v1/proyectos/` + id);
  }

  listProyectTime() {
    return this.http.get(`http://localhost:8081/tiempos/v1/proyectos/tiemposProyectos`);
  }
}
