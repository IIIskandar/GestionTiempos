import { Component, OnInit } from '@angular/core';
import { LoginService } from '../../services/login.service';
import { ActivatedRoute, Router } from '@angular/router';
import { AdminService } from '../../services/admin.service';

@Component({
    selector: 'app-dashboard',
    templateUrl: './dashboard.component.html',
    styleUrls: ['./dashboard.component.scss']
})

export class DashboardComponent implements OnInit {
    constructor(
    private route: ActivatedRoute,
    private login: LoginService,
    private router: Router,
    private admin: AdminService
    ) {}

    // proyectos
    aux1P: any;
    aux1p: Array<any> = [];
    listProyect: Array<{nombre: string, id: string}> = [];

    info: any;
    cc: any;
    nombreBool: boolean;
    user: any;
    timewc: any;
    timesnack: any;
    timemeeting: any;
    nombreTarea: any;
    fInicio: any;
    suspension: boolean;
    proyectos: boolean;
    tareas: boolean;
    fFin: any;
    TT: any;
    numProyect: any;
    auxTime: any;
    timeJobUser: any;
    timeSusUser: any;
    aux: any;
    aux1: any;
    c: any;
    s: any;
    contador: any;
    listSus: Array<{categoria: string, fechaInicio: string, fechaFin: string, tT: string}> = [];

    public pieChartLabels: string[] = ['Tiempo trabajado', 'Tiempo en suspension'];
    public pieChartData: number[] = [];
    public pieChartType: string;

    ngOnInit() {
        this.info = this.route.snapshot.paramMap.get('info');
        if ( this.info === 'suspensiones') {
            this.suspension = true;
            this.proyectos = false;
            this.tareas = false;
        }
        if ( this.info === 'proyectos') {
            this.suspension = false;
            this.proyectos = true;
            this.tareas = false;
        }
        if ( this.info === 'tareas') {
            this.suspension = false;
            this.proyectos = false;
            this.tareas = true;
        }
        if (localStorage.getItem('isLoggedin') === 'true') {
            this.cc = localStorage.getItem('cc');
            this.nombreTarea = localStorage.getItem('nombreTarea');
            if ( this.nombreTarea === null) {
                this.nombreBool = false;
            } else {
                this.nombreBool = true;
            }
            this.getSuspension(this.cc);
            this.getProyectos();
            this.timeJob();
            this.timeSus();
            setTimeout(() => {this.pieChartData = [this.timeJobUser, this.timeSusUser]; } , 500);
            setTimeout(() => {this.pieChartType = 'pie'; } , 500);
        } else {
            localStorage.removeItem('isLoggedin');
            this.router.navigate(['/login']);
        }
    }

    getSuspension(cc) {
        this.login.getUser(cc)
            .subscribe(
                res => {
                    this.user = res;
                    this.aux = this.user.suspensions;
                    if ( this.aux === null) {
                    this.listSus[0] =  {categoria: ' ', fechaInicio: ' ', fechaFin: ' ', tT: ' '};
                    } else {
                        for (let i = 0 ; i < this.aux.length ; i++) {
                            if (this.aux[i].fechaFin === null) {
                            } else {
                                this.fInicio = this.aux[i].fechaInicio.replace('T', '  ').replace('+0000', '');
                                this.fFin = this.aux[i].fechaFin.replace('T', '  ').replace('+0000', '');
                                this.TT = this.aux[i].tiempoSuspension;
                                this.c = this.aux[i].tipoSuspension;
                                this.s = this.aux[i].tiempoSuspension;
                                this.listSus[i] = {categoria: this.c, fechaInicio: this.fInicio, fechaFin: this.fFin, tT: this.TT};
                            }
                        }
                    }
                    if ( this.listSus.length >= 5) {
                        this.contador = this.listSus.length;
                        for (let i = 0; i < (this.contador - 5); i++) {
                            this.listSus.splice(1, 1);
                        }
                    }
                    return(this.listSus);
                }
            );
    }

    getProyectos() {
        this.admin.listProyectUser(localStorage.getItem('cc'))
            .subscribe(
              res => {
                this.aux1 = res;
                  for (let i = 0; i < this.aux1.length; i++) {
                    this.listProyect[i] = {nombre: this.aux1[i].name, id: this.aux1[i].id};
                  }
                this.numProyect = this.listProyect.length;
              }
            );
    }

    enviar(id, nombre) {
        this.router.navigate(['/tareas/' + id + '/' + nombre]);
    }

    timeJob() {
        this.admin.timeJobUSer(localStorage.getItem('cc'))
            .subscribe(
                res => {
                    this.auxTime = res;
                    this.timeJobUser = this.auxTime.jobTimeUser;
                }, error => {
                    this.timeJobUser = 0;
                }
            );
    }

    timeSus() {
        this.admin.timeSusUSer(localStorage.getItem('cc'))
            .subscribe(
                res => {
                    this.timeSusUser = res;
                }
            );
    }
}

