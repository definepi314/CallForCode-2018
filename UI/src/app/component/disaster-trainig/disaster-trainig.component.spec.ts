import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DisasterTrainigComponent } from './disaster-trainig.component';

describe('DisasterTrainigComponent', () => {
  let component: DisasterTrainigComponent;
  let fixture: ComponentFixture<DisasterTrainigComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DisasterTrainigComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DisasterTrainigComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
