import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AdminService } from '../../services/admin.service';
@Component({
  selector: 'app-tarea',
  templateUrl: './tarea.component.html',
  styleUrls: ['./tarea.component.scss']
})
export class TareaComponent implements OnInit {

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private admin: AdminService) { }

    aux1: any;
    aux: Array<any> = [];
    listT: Array<{n: string, c: string, s: string, id: string, exTime: string, jT: string}> = [];
    idProyect: any;
    auxC: any;
    auxEx: any;
    auxJob: any;
    cc: any;
    n: any;
    status: any;
    auxTime: any;
    nombreProyect: any;

  ngOnInit() {
    if (localStorage.getItem('isLoggedin') === 'true') {
      this.idProyect = this.route.snapshot.paramMap.get('id');
      this.nombreProyect = this.route.snapshot.paramMap.get('nombre');
      this.cc = localStorage.getItem('cc');
      this.status = localStorage.getItem('status');
      this.admin.Tareas(this.idProyect)
      .subscribe(
        res => {
          this.aux1 = res;
            for (let i = 0; i < this.aux1.length; i++) {
              this.auxC = this.aux1[i].category;
              this.auxEx = this.aux1[i].expectedTime;
              this.auxJob = this.aux1[i].jobTime;
              this.n = this.aux1[i].name;
              this.listT[i] = {n: this.n, c: this.auxC , s: this.aux1[i].status, id: this.aux1[i].id,
                exTime: this.auxEx, jT: this.getTime(this.auxJob)};
              this.auxTime = parseInt(this.listT[i].exTime, 10) * 60;
              this.listT[i].exTime = this.getTime(this.auxTime);
            }
        }
      );
    } else {
      localStorage.removeItem('isLoggedin');
      this.router.navigate(['/login']);
    }
  }

  iniciar(tarea) {
    if ( localStorage.getItem('status') === 'disponible') {
      this.admin.iniciarTarea(tarea.id, this.cc)
        .subscribe(
          succes => {
            alert('Tarea inicada correctamente');
            localStorage.setItem('status', 'En tarea');
            localStorage.setItem('nombreTarea', tarea.name);
            localStorage.setItem('idTarea', tarea.id);
            localStorage.setItem('idProyect', this.idProyect);
            this.router.navigate(['/dashboard/proyectos']);
            window.location.reload();
          }
        );
      } else {
        alert('No puedes iniciar una tarea si estas en una suspension o tinenes activa otra tarea');
      }
  }


  comprobar(d) {
    if ( d.s === 'Cerrada') {
      return true;
    } else {
      return false;
    }
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
