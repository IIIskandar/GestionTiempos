import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';

@Component({
  selector: 'app-suspension',
  templateUrl: './suspension.component.html',
  styleUrls: ['./suspension.component.scss']
})
export class SuspensionComponent implements OnInit {

  profileForm = new FormGroup({
    tipo: new FormControl(''),
    description: new FormControl(''),
  });

  constructor() { }

  ngOnInit() {
  }

  onSubmit() {
    console.log(this.profileForm.value);
  }

}
