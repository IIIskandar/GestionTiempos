import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators, FormArray, FormBuilder  } from '@angular/forms';

@Component({
  selector: 'app-crear',
  templateUrl: './crear.component.html',
  styleUrls: ['./crear.component.scss']
})
export class CrearComponent implements OnInit {

  constructor(private formBuilder: FormBuilder) { }



  proyectoForm = new FormGroup({
    name: new FormControl('')
  });

  ngOnInit() {
  }

  createItem() {

  }
  onSubmit() {
    console.log(this.proyectoForm.value);

  }
}
