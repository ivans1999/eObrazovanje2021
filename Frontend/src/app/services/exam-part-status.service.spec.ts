import { TestBed } from '@angular/core/testing';

import { ExamPartStatusService } from './exam-part-status.service';

describe('ExamPartStatusService', () => {
  let service: ExamPartStatusService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ExamPartStatusService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
