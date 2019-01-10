import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AsesorService {

  constructor(private http: HttpClient) { }

  private formatErrors(error: any) {
    return  throwError(error.error);
  }

  getTurn() {
    return this.http.post(`http://localhost:8080/turnero/v1/turno/asesor`, {
      'id': '1',
      'nombre' : 'Carlos Lugo'
    }).pipe(map(res => res ));
  }

  getTurn2() {
    return this.http.post(`http://localhost:8080/turnero/v1/turno/rasesor`, {
      'id': '1',
      'nombre' : 'Carlos Lugo'
    }).pipe(map(res => res ));
  }

  cancelTurn() {
    return this.http.get(`http://localhost:8080/turnero/v1/turno/asesor/1/estadoPendiente`).pipe(map(res => res ));
  }

  iniAsesoria() {
    return this.http.get(`http://localhost:8080/turnero/v1/turno`).pipe(map(res => res ));
  }

  finAsesoria() {
    return this.http.post(`http://localhost:8080/turnero/v1/turno/asesor/1/estadoTerminado`, {}).pipe(map(res => res ));
  }

  getTurnLost() {
    return this.http.get(`http://localhost:8080/turnero/v1/turno`).pipe(map(res => res ));
  }
}
