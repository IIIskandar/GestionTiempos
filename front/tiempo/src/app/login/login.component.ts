import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../services/login.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { error } from '@angular/compiler/src/util';
@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss']
})

export class LoginComponent implements OnInit {

    profileForm = new FormGroup({
        usuario: new FormControl ('', [Validators.required]),
        password: new FormControl ('', [Validators.required]),
      });

    datos: any;

    constructor(
        private router: Router,
        private login: LoginService) {}

    ngOnInit() {}

    onLogin() {
        this.login.getUser(this.profileForm.value.usuario)
        .subscribe(
            res => {
                this.datos = res;
                if (this.profileForm.value.password === this.datos.password) {
                    localStorage.setItem('isLoggedin', 'true');
                    localStorage.setItem('nombre', this.datos.name);
                    localStorage.setItem('status', this.datos.status);
                    console.log(res);
                    if ( this.datos.rol === 'admin') {
                        this.router.navigate(['/admin']);
                    } else {
                        this.router.navigate(['/dashboard']);
                    }
                } else {
                    alert('Contraseña incorrecta');
                }
            },
                error => {
                   alert('El usuario no existe');
            }
       );
    }
}
