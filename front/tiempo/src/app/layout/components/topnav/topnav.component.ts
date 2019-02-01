import { Component, OnInit } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { SuspensionService  } from '../../../services/suspension.service';

@Component({
    selector: 'app-topnav',
    templateUrl: './topnav.component.html',
    styleUrls: ['./topnav.component.scss']
})
export class TopnavComponent implements OnInit {
    public pushRightClass: string;
    administrador: boolean;
    iSuspension: boolean;
    fSuspension: boolean;
    tarea: boolean;
    nombreBool: boolean;
    nombreTarea: any;

    constructor(
        public router: Router,
        private translate: TranslateService,
        private suspension1: SuspensionService) {
        this.router.events.subscribe(val => {
            if (val instanceof NavigationEnd && window.innerWidth <= 992 && this.isToggled()) {
                this.toggleSidebar();
            }
        });
    }

    cc: any;
    name: any;

    ngOnInit() {
        this.pushRightClass = 'push-right';
        if ( localStorage.getItem('rol') === 'admin' ) {
            this.administrador = true;
        } else {
            this.administrador = false;
        }
        if ( localStorage.getItem('status') === 'suspension' ) {
            this.fSuspension = true;
        } else {
            this.fSuspension = false;
        }
        if ( localStorage.getItem('status') !== 'suspension' ) {
            this.iSuspension = true;
        } else {
            this.iSuspension = false;
        }
        if ( localStorage.getItem('status') === 'EnTarea') {
            this.tarea = true;
        } else {
            this.tarea = false;
        }
        this.nombreTarea = localStorage.getItem('nombreTarea');
            if ( this.nombreTarea === null) {
                this.nombreBool = false;
            } else {
                this.nombreBool = true;
            }
        this.name = localStorage.getItem('nombre');
    }

    isToggled(): boolean {
        const dom: Element = document.querySelector('body');
        return dom.classList.contains(this.pushRightClass);
    }

    toggleSidebar() {
        const dom: any = document.querySelector('body');
        dom.classList.toggle(this.pushRightClass);
    }

    onLoggedout() {
        localStorage.removeItem('isLoggedin');
        localStorage.removeItem('nombre');
        localStorage.removeItem('status');
        localStorage.removeItem('cc');
        this.router.navigate(['/login']);
    }

    changeLang(language: string) {
        this.translate.use(language);
    }

    lista() {
        this.router.navigate(['/proyectos']);
    }

    configuacion() {
        this.router.navigate(['/adminConfig']);
    }

    dashboard() {
        this.router.navigate(['/dashboard/proyectos']);
    }

    crear() {
        this.router.navigate(['/crear']);
    }

    admin() {
        this.router.navigate(['/admin']);
    }

    finTarea() {
        this.router.navigate(['/finalizar/' + localStorage.getItem('idProyect') + '/' + localStorage.getItem('idTarea')]);
    }

    suspension() {
        if (localStorage.getItem('status') === 'suspension') {
            alert('No puedes iniciar una suspension sin haber finalizado una');
        } else {
            this.router.navigate(['/suspension']);
        }
    }

    finSuspension() {
        this.cc = localStorage.getItem('cc');
        if (localStorage.getItem('status') === 'suspension') {
            this.suspension1.finalizarSuspension(this.cc)
            .subscribe(
                success => {
                    localStorage.setItem('status', 'disponible');
                    setTimeout(() => {this.ngOnInit(); } , 500);
                    alert('Suspension finalizada correctamente');
                  },
                    error => {
                      alert('Error al finalizar la suspension');
                    }
            );
        } else {
            alert('No puedes finalizar una suspension sin haberla iniciado primero');
        }
    }
}
