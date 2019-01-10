import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Message } from '@angular/compiler/src/i18n/i18n_ast';
import { error } from '@angular/compiler/src/util';

@Component({
    selector: 'app-forms',
    templateUrl: './forms.component.html',
    styleUrls: ['./forms.component.scss']
})



export class FormsComponent implements OnInit {

    constructor(
        private route: ActivatedRoute,
        private router: Router) { }

        listurns: Array<{}> = ['hola'];

    ngOnInit() {
    }

    enviar(nombre) {
        console.log(nombre);
        this.router.navigate(['/tareas']);
    }

  }
