import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExamPartDetailComponent } from './exam-part-detail.component';

describe('ExamPartDetailComponent', () => {
  let component: ExamPartDetailComponent;
  let fixture: ComponentFixture<ExamPartDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ExamPartDetailComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ExamPartDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
