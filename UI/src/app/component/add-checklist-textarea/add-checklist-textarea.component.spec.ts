import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddChecklistTextareaComponent } from './add-checklist-textarea.component';

describe('AddChecklistTextareaComponent', () => {
  let component: AddChecklistTextareaComponent;
  let fixture: ComponentFixture<AddChecklistTextareaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddChecklistTextareaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddChecklistTextareaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
