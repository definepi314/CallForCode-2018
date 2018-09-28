import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TutorialVideosComponent } from './tutorial-videos.component';

describe('TutorialVideosComponent', () => {
  let component: TutorialVideosComponent;
  let fixture: ComponentFixture<TutorialVideosComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TutorialVideosComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TutorialVideosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
