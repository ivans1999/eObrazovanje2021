import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewCourseSpecificationComponent } from './view-course-specification.component';

describe('ViewCourseSpecificationComponent', () => {
  let component: ViewCourseSpecificationComponent;
  let fixture: ComponentFixture<ViewCourseSpecificationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewCourseSpecificationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewCourseSpecificationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
