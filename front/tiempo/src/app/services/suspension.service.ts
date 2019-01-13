import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient, HttpParams } from '@angular/common/http';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SuspensionService {

  constructor(private http: HttpClient) { }

  private formatErrors(error: any) {
    return  throwError(error.error);
  }

  crearSuspension(cc, wc, snack, meeting, detalle) {
    return this.http.post(`http://localhost:8081/tiempos/v1/usuarios/suspension/iniciar/` + cc , {
      'wcs': wc,
      'meetings': meeting,
      'snacks': snack,
      'detalleSuspension' : detalle
    });
  }

  finalizarSuspension(cc) {
    return this.http.post(`http://localhost:8081/tiempos/v1/usuarios/suspension/fin/` + cc , {});
  }
}
