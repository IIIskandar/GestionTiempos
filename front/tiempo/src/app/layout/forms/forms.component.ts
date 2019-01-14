import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Message } from '@angular/compiler/src/i18n/i18n_ast';
import { error } from '@angular/compiler/src/util';
import { AdminService } from '../../services/admin.service';
@Component({
    selector: 'app-forms',
    templateUrl: './forms.component.html',
    styleUrls: ['./forms.component.scss']
})



export class FormsComponent implements OnInit {

    constructor(
        private route: ActivatedRoute,
        private router: Router,
        private admin: AdminService) { }

        aux1: any;
        aux: Array<any> = [];
        listProyect: Array<{nombre: string, id: string}> = [];

    ngOnInit() {
        if (localStorage.getItem('isLoggedin') === 'true') {
            this.admin.listProyectUser(localStorage.getItem('cc'))
            .subscribe(
              res => {
                this.aux1 = res;
                  for (let i = 0; i < this.aux1.length; i++) {
                    this.listProyect[i] = {nombre: this.aux1[i].name, id: this.aux1[i].id};
                  }
              }
            );
          } else {
            localStorage.removeItem('isLoggedin');
            this.router.navigate(['/login']);
          }
    }

    enviar(id) {
        this.router.navigate(['/tareas/' + id]);
    }

  }
