import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators, FormArray, FormBuilder  } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { AdminService } from '../../services/admin.service';

@Component({
  selector: 'app-info-user',
  templateUrl: './info-user.component.html',
  styleUrls: ['./info-user.component.scss']
})
export class InfoUserComponent implements OnInit {

  constructor(private formBuilder: FormBuilder,
    private admin: AdminService,
    private route: ActivatedRoute,
    private router: Router) { }

  idUser: any;
  nombreUser: any;
  aux1: any;
  aux2: any;
  listTareas: Array<{nP: string, nT: string, s: string, tT: string}> = [];
  listProyect: Array<{nombre: string, tT: string}> = [];

  ngOnInit() {
    if (localStorage.getItem('isLoggedin') === 'true') {
      this.idUser = this.route.snapshot.paramMap.get('id');
      this.nombreUser = this.route.snapshot.paramMap.get('nombre');
      this.getProyectos();
      this.getTareas();
  } else {
      localStorage.removeItem('isLoggedin');
      this.router.navigate(['/login']);
  }
    if (localStorage.getItem('rol') !== 'admin') {
      this.router.navigate(['/dashboard/proyectos']);
    }
  }

  getTareas() {
    this.admin.listTareasUser(this.idUser)
        .subscribe(
            res => {
                this.aux2 = res;
                for (let i = 0; i < this.aux2.length; i++) {
                    this.listTareas[i] = {nP: this.aux2[i].nameProyecto, nT: this.aux2[i].nameTarea,
                        s: this.aux2[i].status, tT: this.aux2[i].jobTimeUser};
                    this.listTareas[i].tT = this.getTime(this.aux2[i].jobTimeUser );
                }
            }
        );
  }

  getProyectos() {
    this.admin.detalleProyect(this.idUser)
        .subscribe(
          res => {
            this.aux1 = res;
            console.log(res);
              for (let i = 0; i < this.aux1.length; i++) {
                this.listProyect[i] = {nombre: this.aux1[i].name, tT: this.aux1[i].jobTimeUser};
                this.listProyect[i].tT = this.getTime(this.aux1[i].jobTimeUser );
              }
          }
        );
  }

  getTime(value: number): string {
    const  temp = value * 60;
    const hours = Math.floor((temp / 3600));
    const minutes = (temp % 3600) / 60;
    if (minutes < 10) {
        if (hours < 10) {
            return '0' + hours + ':0' + minutes;
        } else {
            return hours + ':0' + minutes;
        }
    } else {
        if (hours < 10) {
            return '0' + hours + ':' + minutes;
        } else {
            return hours + ':' + minutes;
            }
        }
    }

}
