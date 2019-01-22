import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminCrearComponent } from './admin-crear.component';

describe('AdminCrearComponent', () => {
  let component: AdminCrearComponent;
  let fixture: ComponentFixture<AdminCrearComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminCrearComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminCrearComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
