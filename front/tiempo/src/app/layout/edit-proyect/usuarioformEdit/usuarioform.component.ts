import { Component, OnInit, Input } from '@angular/core';
import {Observable} from 'rxjs';
import { FormGroup, FormControl, Validators, FormArray, FormBuilder  } from '@angular/forms';
import { AdminService } from '../../../services/admin.service';
import {map, startWith} from 'rxjs/operators';

@Component({
  selector: 'app-usuarioformedit',
  templateUrl: './usuarioform.component.html',
  styleUrls: ['./usuarioform.component.scss']
})
export class UsuarioformEditComponent implements OnInit {

  listUsers: Array<{nombre: string, cc: string}> = [];
  options: string[] = [''];
  filteredOptions: Observable<string[]>;
  aux: any;
  aux1: any;
  selectedCityIds: string[];

  constructor(private formBuilder: FormBuilder,
    private admin: AdminService,
    ) { }

  @Input() usersId: FormGroup;

  ngOnInit() {
    this.getUsers();
    setTimeout(() => {this.filteredOptions = this.usersId.valueChanges
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
      const filterValue = this.aux1.cc.toLowerCase();
      return this.options.filter(option => option.toLowerCase().includes(filterValue));
    }
  }

  getUsers() {
    this.admin.getUsers()
      .subscribe(
        res => {
          this.aux1 = res;
          for (let i = 0; i < this.aux1.length; i++) {
            this.listUsers[i] = {nombre: this.aux1[i].name, cc: this.aux1[i].cc};
            this.options.push(this.aux1[i].name);
          }
        }
      );
  }

}
