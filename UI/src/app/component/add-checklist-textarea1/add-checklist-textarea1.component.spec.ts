import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddChecklistTextarea1Component } from './add-checklist-textarea1.component';

describe('AddChecklistTextarea1Component', () => {
  let component: AddChecklistTextarea1Component;
  let fixture: ComponentFixture<AddChecklistTextarea1Component>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddChecklistTextarea1Component ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddChecklistTextarea1Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
