import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AccountComponent } from './components/account/account.component';
import { AddExamPartComponent } from './components/add-exam-part/add-exam-part.component';
import { AddExamComponent } from './components/add-exam/add-exam.component';
import { AddPaymentComponent } from './components/add-payment/add-payment.component';
import { CourseInstanceComponent } from './components/course-instance/course-instance.component';
import { CoursesForStudentComponent } from './components/courses-for-student/courses-for-student.component';
import { CoursesSpecificationsComponent } from './components/courses-specifications/courses-specifications.component';
import { CoursesComponent } from './components/courses/courses.component';
import { DocumentComponent } from './components/document/document.component';
import { DocumentsComponent } from './components/documents/documents.component';
import { ExamDetailComponent } from './components/exam-detail/exam-detail.component';
import { ExamPartDetailComponent } from './components/exam-part-detail/exam-part-detail.component';
import { ExamsComponent } from './components/exams/exams.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { StudentDetailComponent } from './components/student-detail/student-detail.component';
import { StudentExamDetailComponent } from './components/student-exam-detail/student-exam-detail.component';
import { StudentComponent } from './components/student/student.component';
import { TeacherComponent } from './components/teacher/teacher.component';
import { TeachersComponent } from './components/teachers/teachers.component';
import { TeachingsComponent } from './components/teachings/teachings.component';
import { UsersComponent } from './components/users/users.component';
import { ViewCourseInstanceComponent } from './components/view-course-instance/view-course-instance.component';
import { ViewCourseSpecificationComponent } from './components/view-course-specification/view-course-specification.component';
import { ViewUserComponent } from './components/view-user/view-user.component';
import { LoginGuard } from './guards/login-guard';
import { RoleGuard } from './guards/role-guard';

const routes: Routes = [
  { path: '', component: HomeComponent, canActivate: [LoginGuard]},
  { path: 'login', component: LoginComponent, canActivate: [LoginGuard]},
  { path: 'home', component: HomeComponent, canActivate: [RoleGuard]},
  { path: 'add-user', component: ViewUserComponent, canActivate: [RoleGuard] },
  { path: 'exams', component: ExamsComponent, canActivate: [RoleGuard] },
  { path: 'exam-detail/student/:examId', component: ExamDetailComponent, canActivate: [RoleGuard] },
  { path: 'teacher-exam-parts/:teacherId', component: ExamDetailComponent, canActivate: [RoleGuard] },
  { path: 'course-instance/exam-parts/:courseId', component: ExamDetailComponent, canActivate: [RoleGuard] },
  { path: 'account', component: AccountComponent, canActivate: [RoleGuard] },
  { path: 'users', component: UsersComponent, canActivate: [RoleGuard] },
  { path: 'students', component: StudentComponent, canActivate: [RoleGuard] },
  { path: 'documents', component: DocumentsComponent, canActivate: [RoleGuard] },
  { path: 'document', component: DocumentComponent, canActivate: [RoleGuard] },
  { path: 'document/:id', component: DocumentComponent, canActivate: [RoleGuard] },
  { path: 'document/add-for/:username', component: DocumentComponent, canActivate: [RoleGuard] },
  { path: 'teachings', component: TeachingsComponent, canActivate: [RoleGuard] },
  { path: 'course-instance/:id', component: CourseInstanceComponent, canActivate: [RoleGuard] },
  { path: 'add-exam', component: AddExamComponent, canActivate: [RoleGuard] },
  { path: 'add-payment', component: AddPaymentComponent, canActivate: [RoleGuard] },
  { path: 'payment/:id', component: AddPaymentComponent, canActivate: [RoleGuard] },
  { path: 'admin-add-payment/:studentUsername', component: AddPaymentComponent, canActivate: [RoleGuard] },
  { path: 'add-exam-part', component: AddExamPartComponent, canActivate: [RoleGuard] },
  { path: 'add-exam-part/:examPartId', component: AddExamPartComponent, canActivate: [RoleGuard] },
  { path: 'view-user/:id', component: ViewUserComponent, canActivate: [RoleGuard] },
  { path: 'courses', component: CoursesComponent, canActivate: [RoleGuard] },
  { path: 'add-course-instance', component: ViewCourseInstanceComponent, canActivate: [RoleGuard] },
  { path: 'courses-specifications', component: CoursesSpecificationsComponent, canActivate: [RoleGuard] },
  { path: 'add-course-specification', component: ViewCourseSpecificationComponent, canActivate: [RoleGuard] },
  { path: 'view-course-specification/:id', component: ViewCourseSpecificationComponent, canActivate: [RoleGuard] },
  { path: 'student-exam-detail/:courseId/:cardNumber', component: StudentExamDetailComponent, canActivate: [RoleGuard] },
  { path: 'student-detail/:id', component: StudentDetailComponent, canActivate: [RoleGuard] },
  { path: 'teachers', component: TeachersComponent, canActivate: [RoleGuard] },
  { path: 'teacher/:id', component: TeacherComponent, canActivate: [RoleGuard] },
  { path: 'courses-for-student', component: CoursesForStudentComponent, canActivate: [RoleGuard] },
  { path: 'exam-part/:code', component: ExamPartDetailComponent, canActivate: [RoleGuard] },
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
