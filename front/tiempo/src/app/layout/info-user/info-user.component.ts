import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators, FormArray, FormBuilder  } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { AdminService } from '../../services/admin.service';

@Component({
  selector: 'app-info-user',
  templateUrl: './info-user.component.html',
  styleUrls: ['./info-user.component.scss']
})
export class InfoUserComponent implements OnInit {

  constructor(private formBuilder: FormBuilder,
    private Admin: AdminService,
    private route: ActivatedRoute,
    private router: Router) { }

  idUser: any;
  nombreUser: any;

  ngOnInit() {
    if (localStorage.getItem('isLoggedin') === 'true') {
      this.idUser = this.route.snapshot.paramMap.get('id');
      this.nombreUser = this.route.snapshot.paramMap.get('nombre');
  } else {
      localStorage.removeItem('isLoggedin');
      this.router.navigate(['/login']);
  }
    if (localStorage.getItem('rol') !== 'admin') {
      this.router.navigate(['/dashboard/proyectos']);
    }
  }

}
