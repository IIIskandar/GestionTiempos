import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { AdminService } from '../../services/admin.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-finalizar',
  templateUrl: './finalizar.component.html',
  styleUrls: ['./finalizar.component.scss']
})
export class FinalizarComponent implements OnInit {

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private admin: AdminService) { }


  profileForm = new FormGroup({
    tipo: new FormControl ('', [Validators.required]),
    description: new FormControl('', [Validators.required]),
  });

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

  onSubmit() {
    this.admin.finalizarTarea(this.idtarea, this.profileForm.value.tipo, this.profileForm.value.description)
      .subscribe(
        success => {
          alert('Tarea finalizada correctamente');
          localStorage.setItem('status' , 'disponible');
          localStorage.removeItem('nombreTarea');
          this.router.navigate(['/dashboard/proyectos']);
          window.location.reload();
        }
      );
  }

}
