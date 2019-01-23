import { Component, OnInit } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import { AdminService  } from '../../services/admin.service';
@Component({
  selector: 'app-admin-crear',
  templateUrl: './admin-crear.component.html',
  styleUrls: ['./admin-crear.component.scss']
})
export class AdminCrearComponent implements OnInit {

  constructor(public router: Router, private admin: AdminService) { }

  ngOnInit() {
  }

  categoria() {
    this.router.navigate(['/adminCategoria']);
  }

  usuario() {
    this.router.navigate(['/adminUser']);
  }

  suspension() {
    this.router.navigate(['/adminSuspension']);
  }

  crearProyecto() {
    this.router.navigate(['/crear']);
  }

  editProyecto() {
    this.router.navigate(['/editProyect']);
  }
}
