import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { map } from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})
export class GenTurnService {

  constructor(private http: HttpClient) { }

  private formatErrors(error: any) {
    return  throwError(error.error);
  }

  externo() {
    return this.http.post(`http://localhost:8080/turnero/v1/turno`, {
      'id': '1',
      'nombre' : 'externo'
    });
  }

  interno() {
    return this.http.post(`http://localhost:8080/turnero/v1/turno`, {
      'id': '2',
      'nombre' : 'interno'
    });
  }

  tercer() {
    return this.http.post(`http://localhost:8080/turnero/v1/turno`, {
      'id': '3',
      'nombre' : 'prioritario'
    });
  }
}
