import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators, FormArray, FormBuilder  } from '@angular/forms';
import { Router, NavigationEnd } from '@angular/router';
import { AdminService } from '../../services/admin.service';

@Component({
  selector: 'app-crear',
  templateUrl: './crear.component.html',
  styleUrls: ['./crear.component.scss']
})
export class CrearComponent implements OnInit {

  constructor(private formBuilder: FormBuilder,
    private Admin: AdminService,
    private router: Router) { }

  myForm: FormGroup;

  a: any;
  b: any;
  proyect: any;
  j: 0;

  ngOnInit() {
    if (localStorage.getItem('isLoggedin') === 'true') {
      this.myForm = this.formBuilder.group({
        nombre: ['', [Validators.required]],
        tareas: this.formBuilder.array([]),
        usuarios: this.formBuilder.array([])
      });
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



  get tareasForms() {
    return this.myForm.get('tareas') as FormArray;
  }

  get usuariosForms() {
    return this.myForm.get('usuarios') as FormArray;
  }

  addTarea() {
    const tarea = this.formBuilder.group({
      nombre: ['', [Validators.required]],
      categoria: ['', [Validators.required]]
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
    this.Admin.crearProyecto(localStorage.getItem('cc'), this.myForm.value.nombre)
      .subscribe(
        res => {
            this.proyect = res;
            this.agregarUser();
        }
      );
  }

  agregarUser() {
    for (let i = 0; i < this.myForm.value.usuarios.length; i++) {
      console.log('user' + i);
      this.Admin.addUsuario(this.myForm.value.usuarios[i].cc, this.proyect.id)
        .subscribe(
         );
    }
    setTimeout(() => {this.agregarTarea(); } , 2000);
  }

  agregarTarea() {
    for (let i = 0; i < this.myForm.value.tareas.length; i++) {
      console.log('tarea' + i);
       this.Admin.addTarea(this.myForm.value.tareas[i].nombre, '', this.proyect.id)
      .subscribe(
      );
    }
    setTimeout(() => {alert('Proyecto creado correctamente'); } , 2000);
    this.router.navigate(['/admin']);
  }

}
