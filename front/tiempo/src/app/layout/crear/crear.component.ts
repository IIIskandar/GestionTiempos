import { Component, OnInit, Input } from '@angular/core';
import { FormGroup, FormControl, Validators, FormArray, FormBuilder} from '@angular/forms';
import { Router, NavigationEnd } from '@angular/router';
import { AdminService } from '../../services/admin.service';


@Component({
  selector: 'app-crear',
  templateUrl: './crear.component.html',
  styleUrls: ['./crear.component.scss']
})
export class CrearComponent implements OnInit {



  constructor(private formBuilder: FormBuilder,
    private admin: AdminService,
    private router: Router,
    ) { }

  listUsers: Array<{nombre: string, cc: string}> = [];
  myForm: FormGroup;
  a: any;
  b: any;
  proyect: any;
  j: 0;
  aux: any;
  aux1: any;
  auxCC: any;

  ngOnInit() {
    if (localStorage.getItem('isLoggedin') === 'true') {
      this.myForm = this.formBuilder.group({
        name: ['', [Validators.required]],
        creator: localStorage.getItem('cc'),
        tareas: this.formBuilder.array([]),
        usersId: this.formBuilder.array([])
      });
      this.getUsers();
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

  getUsers() {
    this.admin.getUsers()
      .subscribe(
        res => {
          this.aux1 = res;
          for (let i = 0; i < this.aux1.length; i++) {
            this.listUsers[i] = {nombre: this.aux1[i].name, cc: this.aux1[i].cc};
          }
        }
      );
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
    for (let i = 0; i < this.myForm.value.usersId.length; i++) {
      this.auxCC = false;
      for (let j = 0; j < this.listUsers.length; j++) {
        if (this.myForm.value.usersId[i].cc === this.listUsers[j].nombre) {
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
        for (let j = 0; j < this.listUsers.length; j++) {
          if (this.myForm.value.usersId[i].cc === this.listUsers[j].nombre) {
            this.myForm.value.usersId[i].cc = this.listUsers[j].cc;
            j++;
          }
        }
      }
      this.admin.crearProyecto(this.myForm.value)
      .subscribe(
        success => {
          alert('Proyecto creado correctamente');
          this.router.navigate(['/admin']);
        }
      );
    }
  }

}
