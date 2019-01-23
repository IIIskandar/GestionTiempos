import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators, FormArray, FormBuilder  } from '@angular/forms';
import { Router, NavigationEnd } from '@angular/router';
import { AdminService } from '../../services/admin.service';

@Component({
  selector: 'app-admin-user',
  templateUrl: './admin-user.component.html',
  styleUrls: ['./admin-user.component.scss']
})
export class AdminUserComponent implements OnInit {

  constructor(private formBuilder: FormBuilder,
    private Admin: AdminService,
    private router: Router) { }

  myForm: FormGroup;

  ngOnInit() {
    if (localStorage.getItem('isLoggedin') === 'true') {
      this.myForm = this.formBuilder.group({
        name: ['', [Validators.required]],
        cc: ['', [Validators.required]],
        password: ['', [Validators.required]],
        rol: ['', [Validators.required]],
        status: 'disponible'
      });
  } else {
      localStorage.removeItem('isLoggedin');
      this.router.navigate(['/login']);
  }
    if (localStorage.getItem('rol') !== 'admin') {
      this.router.navigate(['/dashboard/proyectos']);
    }
  }

  crear() {
    this.Admin.crearUser(this.myForm.value)
      .subscribe(
        success => {
          alert('Usuario creado correctamente');
          this.router.navigate(['/adminConfig']);
        }
    );
  }
}
