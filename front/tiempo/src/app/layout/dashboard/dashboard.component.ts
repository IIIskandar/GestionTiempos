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
    user: any;
    timewc: any;
    timesnack: any;
    timemeeting: any;
    fInicio: any;
    suspension: boolean;
    proyectos: boolean;
    tareas: boolean;
    fFin: any;
    TT: any;
    numProyect: any;
    auxTime: any;
    timeJobUser = 0;
    timeSusUser = 0;
    timeJobUserF: any;
    timeSusUserF: any;
    aux: any;
    aux1: any;
    aux2: any;
    auxChart: any;
    c: any;
    s: any;
    contador: any;
    listSus: Array<{categoria: string, fechaInicio: string, fechaFin: string, tT: string}> = [];
    listTareas: Array<{nP: string, nT: string, s: string, tT: string}> = [];

    public pieChartLabels: string[] = ['Tiempo trabajado', 'Tiempo en suspension'];
    public pieChartData: number[] = [this.timeJobUser, this.timeSusUser];
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
            this.getSuspension(this.cc);
            this.getProyectos();
            this.timeJob();
            this.timeSus();
            this.getTareas();
            setTimeout(() => {this.timeJobUserF = this.getTime(this.timeJobUser); } , 200);
            setTimeout(() => {this.timeSusUserF = this.getTime(this.timeSusUser); } , 200);
            setTimeout(() => {this.pieChartData = [this.timeJobUser, this.timeSusUser]; } , 200);
            this.pieChartType = 'pie';
        } else {
            localStorage.removeItem('isLoggedin');
            this.router.navigate(['/login']);
        }
    }

    chart(e: any): void {
        this.auxChart = e;
        if (this.auxChart.active.length !== 0) {
            if (this.auxChart.active[0]._index === 0) {
                this.router.navigate(['/dashboard/tareas']);
                setTimeout(() => {this.ngOnInit(); } , 200);
            } else {
                this.router.navigate(['/dashboard/suspensiones']);
                setTimeout(() => {this.ngOnInit(); } , 200);
            }
        }
    }


    getTareas() {
        this.admin.listTareasUser(this.cc)
            .subscribe(
                res => {
                    this.aux2 = res;
                    for (let i = 0; i < this.aux2.length; i++) {
                        this.listTareas[i] = {nP: this.aux2[i].nameProyecto, nT: this.aux2[i].nameTarea,
                            s: this.aux2[i].status, tT: this.aux2[i].jobTimeUser};
                        this.listTareas[i].tT = this.getTime(this.aux2[i].jobTimeUser );
                    }
                }
            );
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
                                this.TT = this.getTime(this.TT);
                                this.c = this.aux[i].tipoSuspension;
                                this.s = this.aux[i].tiempoSuspension;
                                this.listSus[i] = {categoria: this.c, fechaInicio: this.fInicio, fechaFin: this.fFin, tT: this.TT};
                            }
                        }
                    }
                    if ( this.listSus.length >= 5) {
                        this.contador = this.listSus.length;
                        for (let i = 0; i < (this.contador - 5); i++) {
                            this.listSus.splice(0, 1);
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

    getTime(value: number): string {
        const  temp = value * 60;
        const hours = Math.floor((temp / 3600));
        const minutes = (temp % 3600) / 60;
        if (minutes < 10) {
            if (hours < 10) {
                return '0' + hours + ':0' + minutes;
            } else {
                return hours + ':0' + minutes;
            }
        } else {
            if (hours < 10) {
                return '0' + hours + ':' + minutes;
            } else {
                return hours + ':' + minutes;
                }
            }
    }

    timeSus() {
        this.admin.timeSusUSer(localStorage.getItem('cc'))
            .subscribe(
                (res: number) => {
                    this.timeSusUser = res;
                }
            );
    }
}

