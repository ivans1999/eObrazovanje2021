import { TestBed } from '@angular/core/testing';

import { ExamPartTypeService } from './exam-part-type.service';

describe('ExamPartTypeService', () => {
  let service: ExamPartTypeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ExamPartTypeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
