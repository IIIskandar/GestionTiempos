import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UsuarioformEditComponent } from './usuarioform.component';

describe('UsuarioformEditComponent', () => {
  let component: UsuarioformEditComponent;
  let fixture: ComponentFixture<UsuarioformEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UsuarioformEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UsuarioformEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
