import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AdminService } from '../../services/admin.service';
@Component({
  selector: 'app-adminproyect',
  templateUrl: './adminproyect.component.html',
  styleUrls: ['./adminproyect.component.scss']
})
export class AdminproyectComponent implements OnInit {

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private admin: AdminService) { }

  aux1: any;
  aux2: any;
  aux3: any;
  aux4: any;
  listProyect: Array<{nombre: string, id: string, jobTime: string}> = [];
  listUser: Array<{nombre: string, cc: string, rol: string}> = [];
  listCategory: Array<{nombre: string, tiempo: string}> = [];
  listSus: Array<{nombre: string, tiempo: string}> = [];

  ngOnInit() {
    if (localStorage.getItem('isLoggedin') === 'true') {
      this.getProyectos();
      this.getUsers();
      this.getTiempoCategoria();
      this.getTiempoSus();
    } else {
      localStorage.removeItem('isLoggedin');
      this.router.navigate(['/login']);
    }
    if (localStorage.getItem('rol') !== 'admin') {
        this.router.navigate(['/dashboard/proyectos']);
    }
  }

  getProyectos() {
    this.admin.listProyect()
        .subscribe(
          res => {
            this.aux1 = res;
              for (let i = 0; i < this.aux1.length; i++) {
                this.listProyect[i] = {nombre: this.aux1[i].name, id: this.aux1[i].id, jobTime: this.aux1[i].jobTime};
                if (this.aux1[i].jobTime === null) {
                  this.listProyect[i].jobTime = '0';
                }
              }
          }
        );
  }

  getUsers() {
    this.admin.getUsers()
      .subscribe(
        res => {
          this.aux2 = res;
          for (let i = 0; i < this.aux2.length; i++) {
            this.listUser[i] = {nombre: this.aux2[i].name, cc: this.aux2[i].cc, rol: this.aux2[i].rol};
          }
        }
      );
  }

  getTiempoCategoria() {
    this.admin.getTiempoCategoria()
      .subscribe(
        res => {
          this.aux3 = res;
          for (let i = 0; i < this.aux3.length; i++) {
            this.listCategory[i] = {nombre: this.aux3[i].category, tiempo: this.aux3[i].jobTimeCategory};
          }
        }
      );
  }

  getTiempoSus() {
    this.admin.getTiempoSus()
      .subscribe(
        res => {
          this.aux4 = res;
          for (let i = 0; i < this.aux4.length; i++) {
            this.listSus[i] = {nombre: this.aux4[i].tipo, tiempo: this.aux4[i].jobTimeSuspension};
          }
        }
      );
  }

  enviar(id, nombre) {
    this.router.navigate(['/infoProyect/' + id + '/' + nombre]);
  }

  enviarUser(id, nombre) {
    this.router.navigate(['/infoUser/' + id + '/' + nombre]);
  }
  crear() {
    this.router.navigate(['/crear']);
  }

}
