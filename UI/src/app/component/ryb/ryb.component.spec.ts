import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RybViewMapComponent } from './ryb-view-map.component';

describe('RybViewMapComponent', () => {
  let component: RybViewMapComponent;
  let fixture: ComponentFixture<RybViewMapComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RybViewMapComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RybViewMapComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
