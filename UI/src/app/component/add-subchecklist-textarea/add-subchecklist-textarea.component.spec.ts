import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddSubchecklistTextareaComponent } from './add-subchecklist-textarea.component';

describe('AddSubchecklistTextareaComponent', () => {
  let component: AddSubchecklistTextareaComponent;
  let fixture: ComponentFixture<AddSubchecklistTextareaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddSubchecklistTextareaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddSubchecklistTextareaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
