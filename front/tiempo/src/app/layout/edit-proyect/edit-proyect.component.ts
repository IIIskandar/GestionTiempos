import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators, FormArray, FormBuilder} from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { AdminService } from '../../services/admin.service';

@Component({
  selector: 'app-edit-proyect',
  templateUrl: './edit-proyect.component.html',
  styleUrls: ['./edit-proyect.component.scss']
})
export class EditProyectComponent implements OnInit {

  constructor(private formBuilder: FormBuilder,
    private admin: AdminService,
    private router: Router,
    private route: ActivatedRoute
    ) { }

  listUsers: Array<{nombre: string, cc: string}> = [];
  listUsers1: Array<{nombre: string, cc: string}> = [];
  listTareas: Array<{nombre: string, categoria: string, exT: string, id: string}> = [];
  myForm: FormGroup;
  a: any;
  b: any;
  proyect: any;
  j: 0;
  listcc = [];
  aux: any;
  aux1: any;
  auxCC: any;
  auxres: any;
  nombreProyect: any;
  idProyect: any;

  ngOnInit() {
    if (localStorage.getItem('isLoggedin') === 'true') {
      this.idProyect = this.route.snapshot.paramMap.get('id');
      this.getProyect();
      this.getUsers();
      this.myForm = this.formBuilder.group({
        tareas: this.formBuilder.array([]),
        usersId: this.formBuilder.array([])
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
    return this.myForm.get('usersId') as FormArray;
  }

  addTarea() {
    const tarea = this.formBuilder.group({
      name: ['', [Validators.required]],
      category: [''],
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

  getProyect() {
    this.admin.getProyect(this.idProyect)
      .subscribe (
        res => {
          this.aux = res;
          this.nombreProyect = this.aux.name;
          if (this.aux.usersId.length > 0) {
            for (let i = 0; i < this.aux.usersId.length; i++) {
              this.listUsers[i] = {nombre: this.aux.usersId[i].name, cc: this.aux.usersId[i].cc};
            }
          }
          if (this.aux.tareas.length > 0) {
            for (let i = 0; i < this.aux.tareas.length; i++) {
              this.listTareas[i] = {nombre: this.aux.tareas[i].name, categoria: this.aux.tareas[i].category,
              exT: this.aux.tareas[i].expectedTime, id: this.aux.tareas[i].id};
            }
          }
        }
      );
  }

  getUsers() {
    this.admin.getUsers()
      .subscribe(
        res => {
          this.aux1 = res;
          for (let i = 0; i < this.aux1.length; i++) {
            this.listUsers1[i] = {nombre: this.aux1[i].name, cc: this.aux1[i].cc};
          }
        }
      );
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

  eliminarUser(cc) {
    this.admin.eliminarUserProyect(this.idProyect, cc)
      .subscribe (
        res => {
          this.auxres = res;
          if (this.auxres === 1) {
            this.listUsers.length = 0;
            this.ngOnInit();
            alert('Usuario eliminado correctamente');
          } else {
            alert('El usuario no se puede eliminar porque tiene registros');
          }
        }
      );
  }

  eliminarTarea(id) {
    this.admin.eliminarTareaProyect(this.idProyect, id)
      .subscribe (
        res => {
          this.auxres = res;
          if (this.auxres === 1) {
            this.listTareas.length = 0;
            this.ngOnInit();
            alert('Tarea eliminada correctamente');
          } else {
            alert('La tarea no se puede eliminar porque tiene registros');
          }
        }
      );
  }


  enviar() {
    for (let i = 0; i < this.myForm.value.usersId.length; i++) {
      this.auxCC = false;
      for (let j = 0; j < this.listUsers1.length; j++) {
        if (this.myForm.value.usersId[i].cc === this.listUsers1[j].nombre) {
          this.auxCC = true;
          j++;
        }
      }
      if (this.auxCC === false) {
        alert('El usuario ' + this.myForm.value.usersId[i].cc + ' no existe');
        break;
      }
    }
    if (this.auxCC === true) {
      for (let i = 0; i < this.myForm.value.usersId.length; i++) {
        for (let j = 0; j < this.listUsers1.length; j++) {
          if (this.myForm.value.usersId[i].cc === this.listUsers1[j].nombre) {
            this.listcc.push(this.listUsers1[j].cc);
            j++;
          }
        }
      }
    }
      if (this.listcc.length !== 0) {
        this.admin.agregarUsuarios(this.idProyect, this.listcc)
        .subscribe(
          success => {
            alert('Usuarios agregados correctamente');
            if (this.tareasForms.length !== 0) {
              this.admin.agregarTareas(this.idProyect, this.tareasForms.value)
              .subscribe(
                success1 => {
                  alert('Tareas creadas correctamente');
                }
              );
            }
          }
        );
      } else {
        if (this.tareasForms.length !== 0) {
          this.admin.agregarTareas(this.idProyect, this.tareasForms.value)
          .subscribe(
            success1 => {
              alert('Tareas creadas correctamente');
            }
          );
        }
      }
      this.router.navigate(['/adminConfig']);
  }

}
