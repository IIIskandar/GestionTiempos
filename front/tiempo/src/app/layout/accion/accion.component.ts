import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AdminService } from '../../services/admin.service';
@Component({
  selector: 'app-accion',
  templateUrl: './accion.component.html',
  styleUrls: ['./accion.component.scss']
})
export class AccionComponent implements OnInit {

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private admin: AdminService) { }

    cc: any;
    idtarea: any;
    status: any;
    idproyect: any;

  ngOnInit() {
    if (localStorage.getItem('isLoggedin') === 'true') {
      this.idtarea = this.route.snapshot.paramMap.get('tarea');
      this.idproyect = this.route.snapshot.paramMap.get('id');
      this.cc = localStorage.getItem('cc');
      this.status = localStorage.getItem('status');
    } else {
      localStorage.removeItem('isLoggedin');
      this.router.navigate(['/login']);
    }
  }

  iniciar() {
    if ( localStorage.getItem('status') === 'disponible') {
      this.admin.iniciarTarea(this.idtarea, this.cc)
        .subscribe(
          succes => {
            alert('Tarea inicada correctamente');
            localStorage.setItem('status', 'En tarea');
          }
        );
      } else {
        alert('No puedes iniciar una tarea si estas en una suspension o tinenes activa otra tarea');
      }
  }

  finalizar() {
    if (localStorage.getItem('status') === 'En tarea') {
      this.router.navigate(['/finalizar/' + this.idproyect + '/' + this.idtarea]);
    } else {
      alert('No puedes finalizar una tarea sin haberla iniciado antes');
    }
  }
}
