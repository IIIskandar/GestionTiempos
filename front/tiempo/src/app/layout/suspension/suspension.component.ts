import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-suspension',
  templateUrl: './suspension.component.html',
  styleUrls: ['./suspension.component.scss']
})
export class SuspensionComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

  Submit(data) {
    console.log(data);
  }

}
