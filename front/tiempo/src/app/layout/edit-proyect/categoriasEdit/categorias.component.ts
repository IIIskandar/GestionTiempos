import { Component, OnInit, Input } from '@angular/core';
import {Observable} from 'rxjs';
import { FormGroup, FormControl, Validators, FormArray, FormBuilder  } from '@angular/forms';
import { AdminService } from '../../../services/admin.service';
import {map, startWith} from 'rxjs/operators';

@Component({
  selector: 'app-categoriasedit',
  templateUrl: './categorias.component.html',
  styleUrls: ['./categorias.component.scss']
})
export class CategoriasEditComponent implements OnInit {


  options: string[] = [''];
  filteredOptions1: Observable<string[]>;
  listCategory: Array<{nombre: string}> = [];
  aux: any;
  aux1: any;

  constructor(private formBuilder: FormBuilder,
    private admin: AdminService) {
  }


  @Input() tareas: FormGroup;


  ngOnInit() {
    this.getListCategoria();
    setTimeout(() => {this.filteredOptions1 = this.tareas.valueChanges
      .pipe(
        startWith(''),
        map(value => this._filter(value))
      ); } , 500);
  }

  private _filter(value): string[] {
    this.aux1 = value;
    if (this.aux1.length === 0) {
      return this.options;
    } else {
      const filterValue = this.aux1.category.toLowerCase();
      return this.options.filter(option => option.toLowerCase().includes(filterValue));
    }
  }

  getListCategoria() {
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
