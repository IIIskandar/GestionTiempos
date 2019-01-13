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

  crearProyecto(cc, name) {
    return this.http.put(`http://localhost:8081/tiempos/v1/proyectos/`, {
      'name': name,
      'creator': cc
    });
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
}
