import { Component, OnInit } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import { AdminService  } from '../../services/admin.service';
@Component({
  selector: 'app-admin-crear',
  templateUrl: './admin-crear.component.html',
  styleUrls: ['./admin-crear.component.scss']
})
export class AdminCrearComponent implements OnInit {

  constructor(public router: Router, private admin: AdminService) { }

  aux1: any;
  aux2: any;
  aux3: any;
  aux4: any;
  listProyect: Array<{nombre: string, id: string, jobTime: string}> = [];
  listUser: Array<{nombre: string, cc: string, rol: string}> = [];
  listCategory: Array<{nombre: string, tiempo: string}> = [];
  listSus: Array<{nombre: string, id: string}> = [];

  ngOnInit() {
    if (localStorage.getItem('isLoggedin') === 'true') {
      this.getUsers();
      this.getSus();
      this.getProyectos();
    } else {
      localStorage.removeItem('isLoggedin');
      this.router.navigate(['/login']);
    }
    if (localStorage.getItem('rol') !== 'admin') {
        this.router.navigate(['/dashboard/proyectos']);
    }
  }

  categoria() {
    this.router.navigate(['/adminCategoria']);
  }

  usuario() {
    this.router.navigate(['/adminUser']);
  }

  suspension() {
    this.router.navigate(['/adminSuspension']);
  }

  crearProyecto() {
    this.router.navigate(['/crear']);
  }

  editProyecto() {
    this.router.navigate(['/editProyect']);
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

  getSus() {
    this.admin.getSus()
      .subscribe(
        res => {
          this.aux4 = res;
          for (let i = 0; i < this.aux4.length; i++) {
            this.listSus[i] = {nombre: this.aux4[i].name, id: this.aux4[i].id};
          }
        }
      );
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

}
