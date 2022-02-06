import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DocumentsStudentComponent } from './documents-student.component';

describe('DocumentsStudentComponent', () => {
  let component: DocumentsStudentComponent;
  let fixture: ComponentFixture<DocumentsStudentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DocumentsStudentComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DocumentsStudentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
