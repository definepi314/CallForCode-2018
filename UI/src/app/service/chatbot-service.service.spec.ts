import { TestBed, inject } from '@angular/core/testing';

import { ChatbotServiceService } from './chatbot-service.service';

describe('ChatbotServiceService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ChatbotServiceService]
    });
  });

  it('should be created', inject([ChatbotServiceService], (service: ChatbotServiceService) => {
    expect(service).toBeTruthy();
  }));
});
