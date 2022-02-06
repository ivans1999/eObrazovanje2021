import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddExamPartComponent } from './add-exam-part.component';

describe('AddExamPartComponent', () => {
  let component: AddExamPartComponent;
  let fixture: ComponentFixture<AddExamPartComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddExamPartComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddExamPartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
