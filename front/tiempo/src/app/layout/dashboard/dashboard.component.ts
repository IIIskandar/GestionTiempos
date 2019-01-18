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
    // suspensiones
    aux: Array<any> = [];
    aux1: any;
    listSus: Array<{categoria: string, fechaInicio: string, fechaFin: string, tT: string}> = [];

    public pieChartLabels: string[] = ['Tiempo trabajado', 'Tiempo en suspension'];
    public pieChartData: number[] = [300, 500];
    public pieChartType: string;

    ngOnInit() {
        this.pieChartType = 'pie';
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
                    if ( this.user.tiempoWC === null) {
                        this.timewc = 0;
                    } else {
                        this.timewc = this.user.tiempoWC;
                    }
                    if ( this.user.tiempoSnacks === null) {
                        this.timesnack = 0;
                    } else {
                        this.timesnack = this.user.tiempoSnacks;
                    }
                    if ( this.user.tiempoMeeting === null) {
                        this.timemeeting = 0;
                    } else {
                        this.timemeeting = this.user.tiempoMeeting;
                    }

                    this.aux1 = res;
                    this.aux[0] = this.aux1.suspensions;
                    if ( this.aux[0] === null) {
                    this.listSus[0] =  {categoria: ' ', fechaInicio: ' ', fechaFin: ' ', tT: ' '};
                    } else {
                        for (let i = 0 ; i < this.aux[0].length ; i++) {
                            if (this.aux1.suspensions[i].wcs === 1) {
                                if (this.aux1.suspensions[i].fechaFin === null) {
                                } else {
                                    this.fInicio = this.aux1.suspensions[i].fechaInicio.replace('T', '  ').replace('+0000', '');
                                    this.fFin = this.aux1.suspensions[i].fechaFin.replace('T', '  ').replace('+0000', '');
                                    this.TT = this.aux1.suspensions[i].tiempoTotal;
                                    this.listSus[i] = {categoria: 'Wc', fechaInicio: this.fInicio, fechaFin: this.fFin, tT: this.TT};
                                }
                            }
                            if (this.aux1.suspensions[i].snacks === 1) {
                                if (this.aux1.suspensions[i].fechaFin === null) {
                                } else {
                                    this.fInicio = this.aux1.suspensions[i].fechaInicio.replace('T', '  ').replace('+0000', '');
                                    this.fFin = this.aux1.suspensions[i].fechaFin.replace('T', '  ').replace('+0000', '');
                                    this.TT = this.aux1.suspensions[i].tiempoTotal;
                                    this.listSus[i] = {categoria: 'Snak', fechaInicio: this.fInicio, fechaFin: this.fFin, tT: this.TT};
                                }
                            }
                            if (this.aux1.suspensions[i].meetings === 1) {
                                if (this.aux1.suspensions[i].fechaFin === null) {
                                } else {
                                    this.fInicio = this.aux1.suspensions[i].fechaInicio.replace('T', '  ').replace('+0000', '');
                                    this.fFin = this.aux1.suspensions[i].fechaFin.replace('T', '  ').replace('+0000', '');
                                    this.TT = this.aux1.suspensions[i].tiempoTotal;
                                    this.listSus[i] = {categoria: 'Meeting', fechaInicio: this.fInicio, fechaFin: this.fFin, tT: this.TT};
                                }
                            }
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
              }
            );
    }

    enviar(id, nombre) {
        this.router.navigate(['/tareas/' + id + '/' + nombre]);
    }
}

