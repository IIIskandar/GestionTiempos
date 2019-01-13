import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router, NavigationEnd } from '@angular/router';
import { SuspensionService } from '../../services/suspension.service';
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
    console.log(this.profileForm.value);
    this.enviar();
  }

  enviar() {
    if ( this.profileForm.value.tipo === 'Wc') {
      this.suspension1.crearSuspension(this.cc, 1, 0, 0, this.profileForm.value.description)
      .subscribe(
        success => {
          localStorage.setItem('stattus', 'suspension');
          alert('Suspension iniciada correctamente');
          this.router.navigate(['/dashboard']);
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
          localStorage.setItem('stattus', 'suspension');
          alert('Suspension iniciada correctamente');
          this.router.navigate(['/dashboard']);
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
          localStorage.setItem('stattus', 'suspension');
          alert('Suspension iniciada correctamente');
          this.router.navigate(['/dashboard']);
        },
          error => {
            alert('Error al iniciar la suspension');
          }
      );
    }
  }
}
