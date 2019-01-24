import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InfoProyectComponent } from './info-proyect.component';

describe('InfoProyectComponent', () => {
  let component: InfoProyectComponent;
  let fixture: ComponentFixture<InfoProyectComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InfoProyectComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InfoProyectComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
