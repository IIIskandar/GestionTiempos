import { Component, OnInit } from '@angular/core';
import { AsesorService } from '../../asesor.service';
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
        private router: Router,
        private asesorService: AsesorService, ) { }

    ngOnInit() {
    }



    pedirTurno() {
        this.asesorService.getTurn()
        .subscribe(
            success => {
                alert('Turno llamado correctamente');
        },
            error => {
                alert('Error al llamar turno');
            }
      );
    }

    RepedirTurno() {
        this.asesorService.getTurn2()
        .subscribe(
            success => {
                alert('El turno se volvio a llamar correctamente');
        },
            error => {
                alert('Error al llamar turno');
            }
      );
    }

    cancelarTurno() {
        this.asesorService.cancelTurn()
        .subscribe(
            success => {
                alert('Turno cancelado correctamente');
        },
            error => {
                alert('Error al cancelar turno');
            }
      );
    }

    iniciarAsesoria() {
        this.asesorService.iniAsesoria()
        .subscribe(
            success => {
                alert('Asesoria iniciada correctamente');
        },
            error => {
                alert('Error al iniciar asesoria');
            }
      );
    }

    finalizarAsesoria() {
        this.asesorService.finAsesoria()
        .subscribe(
            success => {
                alert('Asesoria finalizada correctamente');
        },
            error => {
                alert('Error al finalizar asesoria');
            }
      );
    }

    llamarTurnoPerdido() {
        this.asesorService.getTurnLost()
        .subscribe(
            success => {
                alert('Turno pedido correctamente');
        },
            error => {
                alert('Error al pedir turno');
            }
      );
    }


  }
