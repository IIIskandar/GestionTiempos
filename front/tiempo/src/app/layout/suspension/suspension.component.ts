import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router, NavigationEnd } from '@angular/router';
import { SuspensionService } from '../../services/suspension.service';
import { componentRefresh } from '@angular/core/src/render3/instructions';

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
    private router: Router
  ) { }

  cc: any;

  ngOnInit() {
    if (localStorage.getItem('isLoggedin') === 'true') {
      this.cc = localStorage.getItem('cc');
  } else {
      localStorage.removeItem('isLoggedin');
      this.router.navigate(['/login']);
  }
  }

  onSubmit() {
    this.enviar();
  }

  enviar() {
    if ( localStorage.getItem('status') !== 'suspension') {
      if ( this.profileForm.value.tipo === 'Wc') {
        this.suspension1.crearSuspension(this.cc, 1, 0, 0, this.profileForm.value.description)
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
      if ( this.profileForm.value.tipo === 'Snack') {
        this.suspension1.crearSuspension(this.cc, 0, 1, 0, this.profileForm.value.description)
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
      if ( this.profileForm.value.tipo === 'Reunion') {
        this.suspension1.crearSuspension(this.cc, 0, 0, 1, this.profileForm.value.description)
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
  }
}
