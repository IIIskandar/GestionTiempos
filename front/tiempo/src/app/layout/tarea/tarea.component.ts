import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-tarea',
  templateUrl: './tarea.component.html',
  styleUrls: ['./tarea.component.scss']
})
export class TareaComponent implements OnInit {

  constructor(
    private route: ActivatedRoute,
    private router: Router) { }

    listurns: Array<{}> = [1];

  ngOnInit() {
  }

}
