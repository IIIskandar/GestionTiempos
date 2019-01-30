import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router, NavigationEnd } from '@angular/router';
import { SuspensionService } from '../../services/suspension.service';
import { componentRefresh } from '@angular/core/src/render3/instructions';
import { AdminService } from '../../services/admin.service';

@Component({
  selector: 'app-suspension',
  templateUrl: './suspension.component.html',
  styleUrls: ['./suspension.component.scss']
})
export class SuspensionComponent implements OnInit {

  profileForm = new FormGroup({
    tipo: new FormControl ('', [Validators.required]),
    description: new FormControl ('', [Validators.required]),
  });

  constructor(
    private suspension1: SuspensionService,
    private router: Router,
    private admin: AdminService
  ) { }

  cc: any;
  listSus: Array<{nombre: string, id: string}> = [];
  aux4: any;

  ngOnInit() {
    if (localStorage.getItem('isLoggedin') === 'true' && localStorage.getItem('status') !== 'suspension' ) {
      if (localStorage.getItem('status') !== 'EnTarea') {
        this.cc = localStorage.getItem('cc');
        console.log(localStorage.getItem('status'));
        this.getSus();
      } else {
        this.router.navigate(['/dashboard/proyectos']);
      }
  } else {
      localStorage.removeItem('isLoggedin');
      this.router.navigate(['/login']);
  }
  }

  onSubmit() {
    this.enviar();
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

  enviar() {
    this.suspension1.crearSuspension(this.cc, this.profileForm.value.tipo , this.profileForm.value.description)
      .subscribe(
        success => {
          localStorage.setItem('status', 'suspension');
          alert('Suspension iniciada correctamente');
          this.router.navigateByUrl('/dashboard/proyectos');
          window.location.reload();
        },
          error => {
            alert('Error al iniciar la suspension');
          }
      );
  }
}
