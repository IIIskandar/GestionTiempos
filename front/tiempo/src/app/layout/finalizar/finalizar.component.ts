import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';

@Component({
  selector: 'app-finalizar',
  templateUrl: './finalizar.component.html',
  styleUrls: ['./finalizar.component.scss']
})
export class FinalizarComponent implements OnInit {

  constructor() { }

  profileForm = new FormGroup({
    tipo: new FormControl(''),
    description: new FormControl(''),
  });

  ngOnInit() {
  }

  onSubmit() {
    console.log(this.profileForm.value);
  }

}
