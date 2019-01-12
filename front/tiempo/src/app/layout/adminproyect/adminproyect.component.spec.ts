import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminproyectComponent } from './adminproyect.component';

describe('AdminproyectComponent', () => {
  let component: AdminproyectComponent;
  let fixture: ComponentFixture<AdminproyectComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminproyectComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminproyectComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
