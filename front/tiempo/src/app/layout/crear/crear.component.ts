import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators, FormArray, FormBuilder  } from '@angular/forms';
@Component({
  selector: 'app-crear',
  templateUrl: './crear.component.html',
  styleUrls: ['./crear.component.scss']
})
export class CrearComponent implements OnInit {

  constructor(private formBuilder: FormBuilder) { }

  myForm: FormGroup;

  a: any;
  b: any;


  ngOnInit() {
    this.myForm = this.formBuilder.group({
      nombre: ['', [Validators.required]],
      tareas: this.formBuilder.array([]),
      usuarios: this.formBuilder.array([])
    });

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
      nombre: ['', [Validators.required]]
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
    console.log(this.myForm.value);
  }
}
