import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ZonemarkComponent } from './zonemark.component';

describe('ZonemarkComponent', () => {
  let component: ZonemarkComponent;
  let fixture: ComponentFixture<ZonemarkComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ZonemarkComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ZonemarkComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
