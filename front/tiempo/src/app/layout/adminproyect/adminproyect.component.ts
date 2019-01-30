import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AdminService } from '../../services/admin.service';
import { FormGroup, FormControl, Validators, FormArray, FormBuilder  } from '@angular/forms';
import { DatePipe } from '@angular/common';
@Component({
  selector: 'app-adminproyect',
  templateUrl: './adminproyect.component.html',
  styleUrls: ['./adminproyect.component.scss'],
  providers: [DatePipe]
})
export class AdminproyectComponent implements OnInit {

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private admin: AdminService,
    private formBuilder: FormBuilder,
    private datePipe: DatePipe) { }

  aux1: any;
  aux2: any;
  aux3: any;
  aux4: any;
  aux5: any;
  auxTime: any;
  maxDate1 = this.datePipe.transform(new Date(), 'yyyy-MM-dd');
  maxDate2 = this.datePipe.transform(new Date(), 'yyyy-MM-dd');
  minDate = this.datePipe.transform(new Date(2019, 0, 2), 'yyyy-MM-dd');
  minDate2 = this.datePipe.transform(new Date(2019, 0, 2), 'yyyy-MM-dd');
  fechaInicio = this.datePipe.transform(new Date(), 'yyyy-MM-');
  myForm: FormGroup;
  listProyect: Array<{nombre: string, id: string, jobTime: string, exTime: string}> = [];
  listUser: Array<{nombre: string, cc: string, rol: string}> = [];
  listCategory: Array<{nombre: string, tiempo: string}> = [];
  listSus: Array<{nombre: string, tiempo: string}> = [];

  ngOnInit() {
    if (localStorage.getItem('isLoggedin') === 'true') {
      this.fechaInicio =  this.fechaInicio + '01';
      this.getProyectos(this.fechaInicio, this.maxDate1);
      this.getUsers();
      this.getTiempoCategoria(this.fechaInicio, this.maxDate1);
      this.getTiempoSus(this.fechaInicio, this.maxDate1);
      this.myForm = this.formBuilder.group({
        fechaInicio: ['', [Validators.required]],
        fechaFin: ''
      });
      this.auxTime = 0;
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

  getProyectos(fechaInicio, fechaFin) {
    this.admin.listProyectTimeF(fechaInicio, fechaFin)
        .subscribe(
          res => {
            this.aux1 = res;
              for (let i = 0; i < this.aux1.length; i++) {
                this.listProyect[i] = {nombre: this.aux1[i].name, id: '',
                jobTime: this.aux1[i].jobTimeUser, exTime: ''};
                this.aux5 = this.aux1[i].tareas;
                this.auxTime = 0;
                if (this.aux1[i].jobTimeUser === null) {
                  this.listProyect[i].jobTime = '0';
                }
                this.listProyect[i].jobTime = this.getTime(this.aux1[i].jobTimeUser );
              }
          }
        );
        this.admin.listProyectTime()
        .subscribe(
          res => {
            this.aux1 = res;
              for (let i = 0; i < this.aux1.length; i++) {
                this.aux5 = this.aux1[i].tareas;
                for (let j = 0; j < this.aux5.length; j++) {
                  this.auxTime = this.auxTime + this.aux5[j].expectedTime;
                }
                this.listProyect[i].exTime = this.getTime(this.auxTime * 60);
                this.auxTime = 0;
                this.listProyect[i].id = this.aux1[i].id;
              }
          }
        );
  }


  selectTime() {
    if (this.myForm.value.fechaFin === '') {
      this.myForm.value.fechaFin = this.maxDate2;
      this.myForm.value.fechaInicio = this.datePipe.transform(this.myForm.value.fechaInicio, 'yyyy-MM-dd');
    } else {
      this.myForm.value.fechaInicio = this.datePipe.transform(this.myForm.value.fechaInicio, 'yyyy-MM-dd');
      this.myForm.value.fechaFin = this.datePipe.transform(this.myForm.value.fechaFin, 'yyyy-MM-dd');
    }
    this.getProyectos(this.myForm.value.fechaInicio, this.myForm.value.fechaFin);
    this.getTiempoCategoria(this.myForm.value.fechaInicio, this.myForm.value.fechaFin);
    this.getTiempoSus(this.myForm.value.fechaInicio, this.myForm.value.fechaFin);
  }

  getUsers() {
    this.admin.getUsers()
      .subscribe(
        res => {
          this.aux2 = res;
          for (let i = 0; i < this.aux2.length; i++) {
            this.listUser[i] = {nombre: this.aux2[i].name, cc: this.aux2[i].cc, rol: this.aux2[i].rol};
          }
        }
      );
  }

  getTiempoCategoria(fechaInicio, fechaFin) {
    this.admin.getTiempoCategoria(fechaInicio, fechaFin)
      .subscribe(
        res => {
          this.aux3 = res;
          if (this.aux3 === null) {
            if (this.listCategory.length > 0) {
              this.listCategory.length = 0;
            }
          } else {
              for (let i = 0; i < this.aux3.length; i++) {
                this.listCategory[i] = {nombre: this.aux3[i].category, tiempo: this.aux3[i].jobTimeCategory};
                this.listCategory[i].tiempo = this.getTime(this.aux3[i].jobTimeCategory );
              }
            }
        }
      );
  }

  getTiempoSus(fechaInicio, fechaFin) {
    this.admin.getTiempoSus(fechaInicio, fechaFin)
      .subscribe(
        res => {
          this.aux4 = res;
          if (this.aux4 === null) {
            if (this.listSus.length > 0) {
              this.listSus.length = 0;
            }
          } else {
              for (let i = 0; i < this.aux4.length; i++) {
                this.listSus[i] = {nombre: this.aux4[i].tipo, tiempo: this.aux4[i].jobTimeSuspension};
                this.listSus[i].tiempo = this.getTime(this.aux4[i].jobTimeSuspension );
              }
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
