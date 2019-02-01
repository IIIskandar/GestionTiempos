import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators, FormArray, FormBuilder  } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { AdminService } from '../../services/admin.service';
import { LoginService } from '../../services/login.service';
import { DatePipe } from '@angular/common';
@Component({
  selector: 'app-info-proyect',
  templateUrl: './info-proyect.component.html',
  styleUrls: ['./info-proyect.component.scss'],
  providers: [DatePipe]
})
export class InfoProyectComponent implements OnInit {

  constructor(private formBuilder: FormBuilder,
    private admin: AdminService,
    private login: LoginService,
    private route: ActivatedRoute,
    private router: Router,
    private datePipe: DatePipe) { }

  idProyect: any;
  nombreProyect: any;
  aux1: any;
  aux2: any;
  auxN: any;
  name: any;
  auxtime: any;
  today = new Date();
  fecha = this.today.setDate(this.today.getDate() + 1);
  maxDate1 = this.datePipe.transform(new Date(this.fecha), 'yyyy-MM-dd');
  maxDate2 = this.datePipe.transform(new Date(), 'yyyy-MM-dd');
  minDate = this.datePipe.transform(new Date(2019, 0, 2), 'yyyy-MM-dd');
  minDate2 = this.datePipe.transform(new Date(2019, 0, 2), 'yyyy-MM-dd');
  fechaInicio = this.datePipe.transform(new Date(), 'yyyy-MM-');
  myForm: FormGroup;
  listUser: Array<{cc: string, nombre: string, jT: string}> = [];
  listTareas: Array<{nT: string, c: string, s: string, tT: string, tE: string}> = [];

  ngOnInit() {
    if (localStorage.getItem('isLoggedin') === 'true') {
        this.fechaInicio =  this.fechaInicio + '01';
        this.idProyect = this.route.snapshot.paramMap.get('id');
        this.nombreProyect = this.route.snapshot.paramMap.get('nombre');
        this.getUsers(this.fechaInicio, this.maxDate1);
        this.getTareas(this.fechaInicio, this.maxDate1);
        this.myForm = this.formBuilder.group({
            fechaInicio: ['', [Validators.required]],
            fechaFin: ''
          });
        this.myForm.value.fechaInicio = this.maxDate1;
        this.onChanges();
    } else {
        localStorage.removeItem('isLoggedin');
        this.router.navigate(['/login']);
  }
    if (localStorage.getItem('rol') !== 'admin') {
      this.router.navigate(['/dashboard/proyectos']);
    }
  }

  onChanges(): void {
    this.myForm.valueChanges.subscribe(val => {
      this.minDate2 = this.myForm.value.fechaInicio;
      if (this.myForm.value.fechaFin !== '') {
        this.maxDate1 = this.datePipe.transform(this.myForm.value.fechaFin, 'yyyy-MM-dd');
      }
    });
  }

  selectTime() {
    if (this.myForm.value.fechaFin === '') {
        this.myForm.value.fechaFin = this.datePipe.transform(new Date(), 'yyyy-MM-dd');
        this.myForm.value.fechaInicio = this.datePipe.transform(this.myForm.value.fechaInicio, 'yyyy-MM-dd');
    } else {
        this.myForm.value.fechaInicio = this.datePipe.transform(this.myForm.value.fechaInicio, 'yyyy-MM-dd');
        this.myForm.value.fechaFin = this.datePipe.transform(
            this.myForm.value.fechaFin.setDate(this.myForm.value.fechaFin.getDate() + 1), 'yyyy-MM-dd');
    }
    this.getUsers(this.myForm.value.fechaInicio, this.myForm.value.fechaFin);
    this.getTareas(this.myForm.value.fechaInicio, this.myForm.value.fechaFin);
  }

  getUsers(fechaInicio, fechaFin) {
    this.admin.listUserProyect(this.idProyect, fechaInicio, fechaFin)
        .subscribe(
            res => {
                this.aux2 = res;
                if (this.aux2 !== null) {
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
            }
        );
  }

  getTareas(fechaInicio, fechaFin) {
    this.admin.listTareaProyect(this.idProyect, fechaInicio, fechaFin)
        .subscribe(
            res => {
                this.listTareas.length = 0;
                this.aux1 = res;
                    for (let i = 0; i < this.aux1.length; i++) {
                        this.listTareas[i] = {nT: this.aux1[i].name, c: this.aux1[i].category,
                            s: this.aux1[i].status, tT: this.aux1[i].jobTime, tE: this.aux1[i].expectedTime };
                        this.auxtime = parseInt(this.listTareas[i].tE, 10) * 60;
                        this.listTareas[i].tE = this.getTime(this.auxtime);
                        this.listTareas[i].tT = this.getTime(this.aux1[i].jobTime );
                    }
            }
        );
  }

  getTime(value: number): string {
    const  temp = value * 60;
    const hours = Math.floor((temp / 3600));
    const minutes = (temp % 3600) / 60;
    if (minutes < 10) {
        if (hours < 10) {
            return '0' + hours + ':0' + minutes;
        } else {
            return hours + ':0' + minutes;
        }
    } else {
        if (hours < 10) {
            return '0' + hours + ':' + minutes;
        } else {
            return hours + ':' + minutes;
            }
        }
    }

}
