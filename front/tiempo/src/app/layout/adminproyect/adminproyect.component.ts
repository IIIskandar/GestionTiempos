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
  aux: Array<any> = [];
  listProyect: Array<{nombre: string}> = [];

  ngOnInit() {
    if (localStorage.getItem('isLoggedin') === 'true') {
      this.admin.listProyect()
      .subscribe(
        res => {
          this.aux1 = res;
            for (let i = 0; i < this.aux1.length; i++) {
              this.listProyect[i] = {nombre: this.aux1[i].name};
            }
        }
      );
    } else {
      localStorage.removeItem('isLoggedin');
      this.router.navigate(['/login']);
    }
    if (localStorage.getItem('rol') !== 'admin') {
        this.router.navigate(['/dashboard/proyectos']);
    }
  }

  enviar(nombre) {
    this.router.navigate(['/editar']);
  }

  crear() {
    this.router.navigate(['/crear']);
  }

}
