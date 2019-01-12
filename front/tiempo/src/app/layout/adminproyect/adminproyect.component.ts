import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-adminproyect',
  templateUrl: './adminproyect.component.html',
  styleUrls: ['./adminproyect.component.scss']
})
export class AdminproyectComponent implements OnInit {

  constructor(
    private route: ActivatedRoute,
    private router: Router) { }

  listurns: Array<{}> = ['hola'];

  ngOnInit() {
  }

  enviar(nombre) {
    console.log(nombre);
    this.router.navigate(['/editar']);
  }

  crear() {
    this.router.navigate(['/crear']);
  }

}
