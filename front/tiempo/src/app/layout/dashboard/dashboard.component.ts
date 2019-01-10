import { Component, OnInit } from '@angular/core';
import { DashboardService } from '../../dashboard.service';

@Component({
    selector: 'app-dashboard',
    templateUrl: './dashboard.component.html',
    styleUrls: ['./dashboard.component.scss']
})

export class DashboardComponent implements OnInit {
    constructor(
    private dashboard: DashboardService, ) {}


    aux: Array<any> = [];
    listurns: Array<{categoria: string, nro_turno: string}> = [];
    contador: 0;
    categoria: string;
    asesor: string;
    contador2: 0;

    ngOnInit() {
        this.listTurn();
        this.listTurnPen();
    }

    listTurn() {
        this.dashboard.listTurn()
        .subscribe(
            res => {
                this.aux[0] = res;
                if ( this.aux[0] === null) {
                    this.listurns[0] =  {categoria: ' ', nro_turno: ' '};
                } else {
                    for (let i = 0 ; i < this.aux[0].length ; i++) {
                        this.categoria = res[i].categoria.nombre;
                        this.listurns[i] = {categoria: this.categoria, nro_turno: res[i].nro_turno};
                    }
                }
            return(this.listurns);
            }
      );
    }

    listTurnPen() {
        this.dashboard.listTurnPen()
        .subscribe(
            res => {
                this.aux[0] = res;
                if ( this.aux[0] === null) {
                    this.contador2 = 0;
                } else {
                    this.contador2 = this.aux[0].length;
                    }
            return(this.contador2 - 1);
            }
      );
    }
}

