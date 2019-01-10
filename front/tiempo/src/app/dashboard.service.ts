import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient, HttpParams } from '@angular/common/http';
import { throwError } from 'rxjs';



@Injectable({
  providedIn: 'root'
})

export class DashboardService {

  constructor(private http: HttpClient) { }

  private formatErrors(error: any) {
    return  throwError(error.error);
  }

  listTurn() {
    return this.http.get(`http://localhost:8080/turnero/v1/reportes/asesor/1`);
  }

  listTurnPen() {
    return this.http.get(`http://localhost:8080/turnero/v1/turno/pendientes`);
  }

  Promedio1() {
    return this.http.get(`http://localhost:8080/turnero/v1/reportes/categoria/1`);
  }

  Promedio2() {
    return this.http.get(`http://localhost:8080/turnero/v1/reportes/categoria/2`);
  }

  Promedio3() {
    return this.http.get(`http://localhost:8080/turnero/v1/reportes/categoria/3`);
  }
}
