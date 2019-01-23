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
    return this.http.post(`http://localhost:8081/tiempos/v1/proyectos/`, JSON.stringify(proyect));
  }

  addUsuario(cc, id) {
    return this.http.post(`http://localhost:8081/tiempos/v1/proyectos/asignarUsuario/` + id, {
      'cc': cc,
    });
  }

  addTarea(nombre, category, id) {
    return this.http.put(`http://localhost:8081/tiempos/v1/proyectos/` + id + `/tarea` , {
      'name': nombre,
      'category': category,
      'status': 'Abierto'
    });
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
    return this.http.put(`http://localhost:8081/tiempos/v1/tareas/` + id + `/registro/inicio/En%20Tarea` , {
      'madeBy': cc
    });
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
}
