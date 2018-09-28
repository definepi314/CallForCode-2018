import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SiteViewMapComponent } from './site-view-map.component';

describe('SiteViewMapComponent', () => {
  let component: SiteViewMapComponent;
  let fixture: ComponentFixture<SiteViewMapComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SiteViewMapComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SiteViewMapComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
