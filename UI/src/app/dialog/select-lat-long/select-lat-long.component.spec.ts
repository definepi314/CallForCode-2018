import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SelectLatLongComponent } from './select-lat-long.component';

describe('SelectLatLongComponent', () => {
  let component: SelectLatLongComponent;
  let fixture: ComponentFixture<SelectLatLongComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SelectLatLongComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SelectLatLongComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
