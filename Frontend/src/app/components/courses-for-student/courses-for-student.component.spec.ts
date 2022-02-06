import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CoursesForStudentComponent } from './courses-for-student.component';

describe('CoursesForStudentComponent', () => {
  let component: CoursesForStudentComponent;
  let fixture: ComponentFixture<CoursesForStudentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CoursesForStudentComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CoursesForStudentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
