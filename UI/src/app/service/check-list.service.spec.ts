import { TestBed, inject } from '@angular/core/testing';

import { CheckListService } from './check-list.service';

describe('CheckListService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [CheckListService]
    });
  });

  it('should be created', inject([CheckListService], (service: CheckListService) => {
    expect(service).toBeTruthy();
  }));
});
