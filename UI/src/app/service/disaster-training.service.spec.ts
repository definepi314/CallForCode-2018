import { TestBed, inject } from '@angular/core/testing';

import { DisasterTrainingService } from './disaster-training.service';

describe('DisasterTrainingService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [DisasterTrainingService]
    });
  });

  it('should be created', inject([DisasterTrainingService], (service: DisasterTrainingService) => {
    expect(service).toBeTruthy();
  }));
});
