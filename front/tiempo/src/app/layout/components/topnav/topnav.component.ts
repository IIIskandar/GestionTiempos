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

    dashboard() {
        this.router.navigate(['/dashboard']);
    }

    suspension() {
        this.router.navigate(['/suspension']);
    }

    finSuspension() {
        this.cc = localStorage.getItem('cc');
        this.suspension1.finalizarSuspension(this.cc)
            .subscribe(
                success => {
                    localStorage.setItem('stattus', 'disponible');
                    alert('Suspension finalizada correctamente');
                  },
                    error => {
                      alert('Error al finalizar la suspension');
                    }
            );
    }
}
