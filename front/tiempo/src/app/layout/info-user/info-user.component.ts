import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators, FormArray, FormBuilder  } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { AdminService } from '../../services/admin.service';
import { DatePipe } from '@angular/common';
@Component({
  selector: 'app-info-user',
  templateUrl: './info-user.component.html',
  styleUrls: ['./info-user.component.scss'],
  providers: [DatePipe]
})
export class InfoUserComponent implements OnInit {

  constructor(private formBuilder: FormBuilder,
    private admin: AdminService,
    private route: ActivatedRoute,
    private router: Router,
    private datePipe: DatePipe) { }

  idUser: any;
  nombreUser: any;
  aux1: any;
  aux2: any;
  aux3: any;
  today = new Date();
  fecha = this.today.setDate(this.today.getDate() + 1);
  maxDate1 = this.datePipe.transform(new Date(this.fecha), 'yyyy-MM-dd');
  maxDate2 = this.datePipe.transform(new Date(), 'yyyy-MM-dd');
  minDate = this.datePipe.transform(new Date(2019, 0, 2), 'yyyy-MM-dd');
  minDate2 = this.datePipe.transform(new Date(2019, 0, 2), 'yyyy-MM-dd');
  fechaInicio = this.datePipe.transform(new Date(), 'yyyy-MM-');
  myForm: FormGroup;
  listTareas: Array<{nP: string, nT: string, s: string, tT: string}> = [];
  listProyect: Array<{nombre: string, tT: string}> = [];
  listSus: Array<{nombre: string, tiempo: string, fehcaInicio: string, fechaFin: string}> = [];

  ngOnInit() {
    if (localStorage.getItem('isLoggedin') === 'true') {
        this.fechaInicio =  this.fechaInicio + '01';
        this.idUser = this.route.snapshot.paramMap.get('id');
        this.nombreUser = this.route.snapshot.paramMap.get('nombre');
        this.getProyectos(this.fechaInicio, this.maxDate1);
        this.getTareas(this.fechaInicio, this.maxDate1);
        this.getSus(this.fechaInicio, this.maxDate1);
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
    this.getProyectos(this.myForm.value.fechaInicio, this.myForm.value.fechaFin);
    this.getTareas(this.myForm.value.fechaInicio, this.myForm.value.fechaFin);
    this.getSus(this.myForm.value.fechaInicio, this.myForm.value.fechaFin);
  }

  getSus(fechaInicio, fechaFin) {
      this.admin.getSusF(this.idUser, fechaInicio, fechaFin)
        .subscribe (
            res => {
                this.listSus.length = 0;
                this.aux3 = res;
                for (let i = 0; i < this.aux3.length; i++) {
                    this.listSus[i] = {nombre: this.aux3[i].tipoSuspension, tiempo: this.aux3[i].tiempoSuspension,
                        fehcaInicio: this.aux3[i].fechaInicio.replace('T', '  ').replace('+0000', ''),
                        fechaFin: this.aux3[i].fechaFin.replace('T', '  ').replace('+0000', '')};
                    this.listSus[i].tiempo = this.getTime(this.aux3[i].tiempoSuspension );
                }
            }
        );
  }

  getTareas(fechaInicio, fechaFin) {
    this.admin.listTareasUserF(this.idUser, fechaInicio, fechaFin)
        .subscribe(
            res => {
                this.listTareas.length = 0;
                this.aux2 = res;
                    for (let i = 0; i < this.aux2.length; i++) {
                        this.listTareas[i] = {nP: this.aux2[i].nameProyecto, nT: this.aux2[i].nameTarea,
                            s: this.aux2[i].status, tT: this.aux2[i].jobTimeUser};
                        this.listTareas[i].tT = this.getTime(this.aux2[i].jobTimeUser );
                    }
            }
        );
  }

  getProyectos(fechaInicio, fechaFin) {
    this.admin.detalleProyect(this.idUser, fechaInicio, fechaFin)
        .subscribe(
          res => {
            this.listProyect.length = 0;
            this.aux1 = res;
                for (let i = 0; i < this.aux1.length; i++) {
                    this.listProyect[i] = {nombre: this.aux1[i].name, tT: this.aux1[i].jobTimeUser};
                    this.listProyect[i].tT = this.getTime(this.aux1[i].jobTimeUser );
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
