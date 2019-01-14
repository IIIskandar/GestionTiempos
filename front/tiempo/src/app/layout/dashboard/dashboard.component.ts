import { Component, OnInit } from '@angular/core';
import { DashboardService } from '../../dashboard.service';
import { LoginService } from '../../services/login.service';
import { Router, NavigationEnd } from '@angular/router';
@Component({
    selector: 'app-dashboard',
    templateUrl: './dashboard.component.html',
    styleUrls: ['./dashboard.component.scss']
})

export class DashboardComponent implements OnInit {
    constructor(
    private dashboard: DashboardService,
    private login: LoginService,
    private router: Router
    ) {}

    cc: any;
    user: any;
    timewc: any;
    timesnack: any;
    timemeeting: any;
    fechaInicio: any;
    fechaFin: any;
    aux: Array<any> = [];
    aux1: any;
    listSuspension: Array<{categoria: string, fechaInicio: string, fechaFin: string}> = [];

    ngOnInit() {
        if (localStorage.getItem('isLoggedin') === 'true') {
            this.cc = localStorage.getItem('cc');
            this.getUser(this.cc);
        } else {
            localStorage.removeItem('isLoggedin');
            this.router.navigate(['/login']);
        }
    }

    getUser(cc) {
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
                    this.listSuspension[0] =  {categoria: ' ', fechaInicio: ' ', fechaFin: ' '};
                    } else {
                        for (let i = 0 ; i < this.aux[0].length ; i++) {
                            if (this.aux1.suspensions[i].wcs === 1) {
                                if (this.aux1.suspensions[i].fechaFin === null) {
                                } else {
                                    this.fechaInicio = this.aux1.suspensions[i].fechaInicio.replace('T', '  ').replace('+0000', '');
                                    this.fechaFin = this.aux1.suspensions[i].fechaFin.replace('T', '  ').replace('+0000', '');
                                    this.listSuspension[i] = {categoria: 'Wc', fechaInicio: this.fechaInicio, fechaFin: this.fechaFin};
                                }
                            }
                            if (this.aux1.suspensions[i].snacks === 1) {
                                if (this.aux1.suspensions[i].fechaFin === null) {
                                } else {
                                    this.fechaInicio = this.aux1.suspensions[i].fechaInicio.replace('T', '  ').replace('+0000', '');
                                    this.fechaFin = this.aux1.suspensions[i].fechaFin.replace('T', '  ').replace('+0000', '');
                                    this.listSuspension[i] = {categoria: 'Snak', fechaInicio: this.fechaInicio, fechaFin: this.fechaFin};
                                }
                            }
                            if (this.aux1.suspensions[i].meetings === 1) {
                                if (this.aux1.suspensions[i].fechaFin === null) {
                                } else {
                                    this.fechaInicio = this.aux1.suspensions[i].fechaInicio.replace('T', '  ').replace('+0000', '');
                                    this.fechaFin = this.aux1.suspensions[i].fechaFin.replace('T', '  ').replace('+0000', '');
                                    this.listSuspension[i] = {categoria: 'Meeting', fechaInicio: this.fechaInicio, fechaFin: this.fechaFin};
                                }
                            }
                        }
                    }
                    return(this.listSuspension);
                }
            );
    }
}

