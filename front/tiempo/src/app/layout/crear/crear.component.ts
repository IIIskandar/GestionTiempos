import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators, FormArray, FormBuilder  } from '@angular/forms';
import { Router, NavigationEnd } from '@angular/router';
import { AdminService } from '../../services/admin.service';
import {Observable} from 'rxjs';
import {map, startWith} from 'rxjs/operators';
import { element } from '@angular/core/src/render3';

@Component({
  selector: 'app-crear',
  templateUrl: './crear.component.html',
  styleUrls: ['./crear.component.scss']
})
export class CrearComponent implements OnInit {

  constructor(private formBuilder: FormBuilder,
    private admin: AdminService,
    private router: Router) { }

  myForm: FormGroup;
  options: string[] = [''];
  filteredOptions1: Observable<string[]>;
  listCategory: Array<{nombre: string}> = [];
  listUsers: Array<{nombre: string, cc: string}> = [];
  a: any;
  b: any;
  proyect: any;
  j: 0;
  aux: any;
  aux1: any;
  aux2: any;

  ngOnInit() {
    if (localStorage.getItem('isLoggedin') === 'true') {
      this.myForm = this.formBuilder.group({
        name: ['', [Validators.required]],
        creator: localStorage.getItem('cc'),
        tareas: this.formBuilder.array([]),
        usersId: this.formBuilder.array([])
      });
      this.getTiempoCategoria();
      this.getUsers();
      this.aux1 = {tareas: [{category: ''}]};
      setTimeout(() => {this.filteredOptions1 = this.tareasForms.valueChanges
          .pipe(
            startWith(''),
            map(value => this._filter(value))
          ); } , 500);
  } else {
      localStorage.removeItem('isLoggedin');
      this.router.navigate(['/login']);
  }
    if (localStorage.getItem('rol') !== 'admin') {
      this.router.navigate(['/dashboard/proyectos']);
    }
    this.addTarea();
    this.addUser();
  }

  private _filter(value): string[] {
    return this.options;
  }


  getUsers() {
    this.admin.getUsers()
      .subscribe(
        res => {
          this.aux2 = res;
          for (let i = 0; i < this.aux2.length; i++) {
            this.listUsers[i] = {nombre: this.aux2[i].name, cc: this.aux2[i].cc};
          }
        }
      );
  }

  getTiempoCategoria() {
    this.admin.getTiempoCategoria()
      .subscribe(
        res => {
          this.aux = res;
          for (let i = 0; i < this.aux.length; i++) {
            this.listCategory[i] = {nombre: this.aux[i].category};
            this.options.push(this.listCategory[i].nombre);
          }
        }
      );
  }

  get tareasForms() {
    return this.myForm.get('tareas') as FormArray;
  }

  get usuariosForms() {
    return this.myForm.get('usersId') as FormArray;
  }

  addTarea() {
    const tarea = this.formBuilder.group({
      name: ['', [Validators.required]],
      category: ['', [Validators.required]],
      status: 'activa',
      expectedTime: ['', [Validators.required]]
    });
    this.tareasForms.push(tarea);
  }

  deteleTarea() {
    this.a = this.tareasForms.length;
    this.tareasForms.removeAt(this.a - 1);
    this.a = 0;
  }


  addUser() {
    const user = this.formBuilder.group({
      cc: ['', [Validators.required]]
    });
    this.usuariosForms.push(user);
  }

  deteleUser() {
    this.b = this.usuariosForms.length;
    this.usuariosForms.removeAt(this.a - 1);
    this.b = 0;
  }

  enviar() {
    this.admin.crearProyecto(this.myForm.value)
      .subscribe(
        success => {
          alert('Proyecto creado correctamente');
          this.router.navigate(['/admin']);
        }
      );
  }
}
