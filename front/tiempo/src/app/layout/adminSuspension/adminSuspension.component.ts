import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-suspension',
  templateUrl: './adminSuspension.component.html',
  styleUrls: ['./adminSuspension.component.scss']
})
export class AdminSuspensionComponent implements OnInit {

  constructor(
    private router: Router) { }

  ngOnInit() {
  }

}
