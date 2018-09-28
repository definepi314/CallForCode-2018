import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PopullationViewMapComponent } from './popullation-view-map.component';

describe('PopullationViewMapComponent', () => {
  let component: PopullationViewMapComponent;
  let fixture: ComponentFixture<PopullationViewMapComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PopullationViewMapComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PopullationViewMapComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
