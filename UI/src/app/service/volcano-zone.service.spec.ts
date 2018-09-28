import { TestBed, inject } from '@angular/core/testing';

import { VolcanoZoneService } from './volcano-zone.service';

describe('VolcanoZoneService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [VolcanoZoneService]
    });
  });

  it('should be created', inject([VolcanoZoneService], (service: VolcanoZoneService) => {
    expect(service).toBeTruthy();
  }));
});
