import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FillSubChecklistComponent } from './fill-sub-checklist.component';

describe('FillSubChecklistComponent', () => {
  let component: FillSubChecklistComponent;
  let fixture: ComponentFixture<FillSubChecklistComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FillSubChecklistComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FillSubChecklistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
