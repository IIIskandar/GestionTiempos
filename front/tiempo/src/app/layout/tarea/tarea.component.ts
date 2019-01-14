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
    listTareas: Array<{name: string, category: string, status: string, id: string}> = [];

    idProyect: any;

  ngOnInit() {
    if (localStorage.getItem('isLoggedin') === 'true') {
      this.idProyect = this.route.snapshot.paramMap.get('id');
      this.admin.Tareas(this.idProyect)
      .subscribe(
        res => {
          this.aux1 = res;
            for (let i = 0; i < this.aux1.length; i++) {
              this.listTareas[i] = {name: this.aux1[i].name, category: 'pendiente', status: this.aux1[i].status, id: this.aux1[i].id};
            }
        }
      );
    } else {
      localStorage.removeItem('isLoggedin');
      this.router.navigate(['/login']);
    }
  }

  enviar(proyect) {
    this.router.navigate(['/accion/' + this.idProyect + '/' + proyect.id]);
  }

}
