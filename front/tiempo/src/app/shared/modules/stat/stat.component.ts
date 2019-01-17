import { Component, OnInit, Input } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import { DashboardComponent } from '../../../layout/dashboard/dashboard.component';
@Component({
    selector: 'app-stat',
    templateUrl: './stat.component.html',
    styleUrls: ['./stat.component.scss']
})
export class StatComponent implements OnInit {
    @Input() bgClass: string;
    @Input() icon: string;
    @Input() count: number;
    @Input() label: string;
    @Input() data: number;

    constructor(public router: Router, public dash: DashboardComponent) {}

    ngOnInit() {}


    enviar(label) {
        if ( label === 'Total tiempo suspensiones') {
            this.router.navigate(['/dashboard/suspensiones']);
            setTimeout(() => {this.dash.ngOnInit(); } , 500);
        }
        if ( label === 'Total tiempo trabajado') {
            this.router.navigate(['/dashboard/tareas']);
            setTimeout(() => {this.dash.ngOnInit(); } , 500);

        }
        if ( label === 'Numero de proyectos') {
            this.router.navigate(['/dashboard/proyectos']);
            setTimeout(() => {this.dash.ngOnInit(); } , 500);
        }
    }
}
