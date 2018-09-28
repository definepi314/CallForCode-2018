import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogZOneComponent } from './dialog-zone.component';

describe('DialogZOneComponent', () => {
  let component: DialogZOneComponent;
  let fixture: ComponentFixture<DialogZOneComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DialogZOneComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogZOneComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
