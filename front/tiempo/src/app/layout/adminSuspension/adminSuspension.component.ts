import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AdminService } from '../../services/admin.service';
import { FormGroup, FormControl, Validators, FormArray, FormBuilder  } from '@angular/forms';
@Component({
  selector: 'app-admin-suspension',
  templateUrl: './adminSuspension.component.html',
  styleUrls: ['./adminSuspension.component.scss']
})
export class AdminSuspensionComponent implements OnInit {

    constructor(private formBuilder: FormBuilder,
      private Admin: AdminService,
      private router: Router) { }

    myForm: FormGroup;

    ngOnInit() {
      if (localStorage.getItem('isLoggedin') === 'true') {
        this.myForm = this.formBuilder.group({
          name: ['', [Validators.required]]
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
      this.Admin.crearTipoSus(this.myForm.value)
        .subscribe(
          success => {
            alert('Tipo de suspension creada correctamente');
            this.router.navigate(['/adminConfig']);
          }
      );
    }

}
