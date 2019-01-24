import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators, FormArray, FormBuilder  } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { AdminService } from '../../services/admin.service';
import { LoginService } from '../../services/login.service';

@Component({
  selector: 'app-info-proyect',
  templateUrl: './info-proyect.component.html',
  styleUrls: ['./info-proyect.component.scss']
})
export class InfoProyectComponent implements OnInit {

  constructor(private formBuilder: FormBuilder,
    private admin: AdminService,
    private login: LoginService,
    private route: ActivatedRoute,
    private router: Router) { }

  idProyect: any;
  nombreProyect: any;
  aux1: any;
  aux2: any;
  auxN: any;
  name: any;
  listUser: Array<{cc: string, nombre: string, jT: string}> = [];
  listTareas: Array<{nP: string, nT: string, s: string, tT: string}> = [];

  ngOnInit() {
    if (localStorage.getItem('isLoggedin') === 'true') {
      this.idProyect = this.route.snapshot.paramMap.get('id');
      this.nombreProyect = this.route.snapshot.paramMap.get('nombre');
      this.getUsers();
  } else {
      localStorage.removeItem('isLoggedin');
      this.router.navigate(['/login']);
  }
    if (localStorage.getItem('rol') !== 'admin') {
      this.router.navigate(['/dashboard/proyectos']);
    }
  }

  getUsers() {
    this.admin.listUserProyect(this.idProyect)
        .subscribe(
            res => {
                this.aux2 = res;
                console.log(res);
                for (let i = 0; i < this.aux2.length; i++) {
                  this.login.getUser(this.aux2[i].cc)
                    .subscribe(
                        res1 => {
                          this.auxN = res1;
                          this.listUser[i] = {cc: this.aux2[i].cc, nombre: this.auxN.name,
                            jT: this.aux2[i].jobTimeUser};
                        this.listUser[i].jT = this.getTime(this.aux2[i].jobTimeUser );
                        }
                    );
              }
            }
        );
  }


  getTime(value: number): string {
    const  temp = value * 60;
    const hours = Math.floor((temp / 3600));
    const minutes: number = Math.floor(temp / 60);
    return hours + ':' + minutes;
  }

}
