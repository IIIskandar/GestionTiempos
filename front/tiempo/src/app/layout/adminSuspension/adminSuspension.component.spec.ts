import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminSuspensionComponent } from './adminSuspension.component';

describe('AdminSuspensionComponent', () => {
  let component: AdminSuspensionComponent;
  let fixture: ComponentFixture<AdminSuspensionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminSuspensionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminSuspensionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
