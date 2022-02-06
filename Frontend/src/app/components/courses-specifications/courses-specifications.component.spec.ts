import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CoursesSpecificationsComponent } from './courses-specifications.component';

describe('CoursesSpecificationsComponent', () => {
  let component: CoursesSpecificationsComponent;
  let fixture: ComponentFixture<CoursesSpecificationsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CoursesSpecificationsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CoursesSpecificationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
