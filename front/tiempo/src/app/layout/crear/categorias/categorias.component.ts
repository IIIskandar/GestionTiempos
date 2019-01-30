import { Component, OnInit, Input } from '@angular/core';
import {Observable} from 'rxjs';
import { FormGroup, FormControl, Validators, FormArray, FormBuilder  } from '@angular/forms';
import { AdminService } from '../../../services/admin.service';
import {map, startWith} from 'rxjs/operators';

@Component({
  selector: 'app-categorias',
  templateUrl: './categorias.component.html',
  styleUrls: ['./categorias.component.scss']
})
export class CategoriasComponent implements OnInit {


  options: string[] = [''];
  filteredOptions1: Observable<string[]>;
  listCategory: Array<{nombre: string}> = [];
  aux: any;

  constructor(private formBuilder: FormBuilder,
    private admin: AdminService) {
  }


  @Input() tareas: FormGroup;


  ngOnInit() {
    setTimeout(() => {this.filteredOptions1 = this.tareas.valueChanges
      .pipe(
        startWith(''),
        map(value => this._filter(value))
      ); } , 500);
  }

  private _filter(value): string[] {
    return this.options;
  }

  getTiempoCategoria() {
    this.admin.getListCategoria()
      .subscribe(
        res => {
          this.aux = res;
          for (let i = 0; i < this.aux.length; i++) {
            this.listCategory[i] = {nombre: this.aux[i].name};
            this.options.push(this.listCategory[i].nombre);
          }
        }
      );
  }

}
