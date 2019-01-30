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
    return this.http.post(`http://localhost:8081/tiempos/v1/tareas/` + id + `/registro/inicio/EnTarea` , {
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

  getTiempoCategoria(fechaInicio, fechaFin) {
    return this.http.get(`http://localhost:8081/tiempos/v1/tareas/tiempoCategorias?fechaInicio=` + fechaInicio +
    '&fechaFin=' + fechaFin);
  }

  getListCategoria() {
    return this.http.get(`http://localhost:8081/tiempos/v1/tareas/categorias`);
  }

  getTiempoSus(fechaInicio, fechaFin) {
    return this.http.get(`http://localhost:8081/tiempos/v1/suspension/tiempo?fechaInicio=` + fechaInicio +
    '&fechaFin=' + fechaFin);
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

  listTareasUserF(cc, fechaInicio, fechaFin) {
    return this.http.get(`http://localhost:8081/tiempos/v1/usuarios/tiempoTareaFecha/` + cc
    + '?fechaInicio=' + fechaInicio + '&fechaFin=' + fechaFin);
  }

  listUserProyect(id, fechaInicio, fechaFin) {
    return this.http.get(`http://localhost:8081/tiempos/v1/proyectos/usuariosProyecto/` + id
    + '?fechaInicio=' + fechaInicio + '&fechaFin=' + fechaFin);
  }

  listTareaProyect(id, fechaInicio, fechaFin) {
    return this.http.get(`http://localhost:8081/tiempos/v1/proyectos/tareasProyecto/` + id
    + '?fechaInicio=' + fechaInicio + '&fechaFin=' + fechaFin);
  }

  detalleProyect(cc, fechaInicio, fechaFin) {
    return this.http.get(`http://localhost:8081/tiempos/v1/usuarios/tiempoProyecto/` + cc
    + '?fechaInicio=' + fechaInicio + '&fechaFin=' + fechaFin);
  }

  tiempoJobProyect(id) {
    return this.http.get(`http://localhost:8081/tiempos/v1/proyectos/` + id);
  }

  listProyectTime() {
    return this.http.get(`http://localhost:8081/tiempos/v1/proyectos/tiempoProyectos`);
  }

  listProyectTimeF(fechaInicio, fechaFin) {
    return this.http.get(`http://localhost:8081/tiempos/v1/proyectos/tiempoProyectosFecha?fechaInicio=` + fechaInicio +
    '&fechaFin=' + fechaFin);
  }

  eliminarSus(nombre){
    return this.http.delete(`http://localhost:8081/tiempos/v1/suspension/eliminar/` + nombre);
  }

  eliminarUser(cc){
    return this.http.delete(`http://localhost:8081/tiempos/v1/usuarios/` + cc);
  }

  eliminarTarea(idProyecto, idTarea){
    return this.http.delete(`http://localhost:8081/tiempos/v1/proyectos/eliminarTarea/` + idProyecto + `/` + idTarea);
  }
}
