import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-finalizar',
  templateUrl: './finalizar.component.html',
  styleUrls: ['./finalizar.component.scss']
})
export class FinalizarComponent implements OnInit {

  constructor() { }

  profileForm = new FormGroup({
    tipo: new FormControl ('', [Validators.required]),
    description: new FormControl('', [Validators.required]),
  });

  ngOnInit() {
  }

  onSubmit() {
    console.log(this.profileForm.value);
  }

}
