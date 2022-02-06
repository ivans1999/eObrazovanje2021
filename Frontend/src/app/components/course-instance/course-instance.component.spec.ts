import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CourseInstanceComponent } from './course-instance.component';

describe('CourseInstanceComponent', () => {
  let component: CourseInstanceComponent;
  let fixture: ComponentFixture<CourseInstanceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CourseInstanceComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CourseInstanceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
