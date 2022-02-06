import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { CourseInstance } from 'src/app/model/courseInstance';
import { CourseSpecification } from 'src/app/model/courseSpecification';
import { Enrollment } from 'src/app/model/enrollment';
import { AuthenticationService } from 'src/app/services/authentication.service';

@Injectable({
  providedIn: 'root'
})
export class CoursesService {

  private coursesInstanceUrl = 'api/course-instance';

  private coursesSpecificationUrl = 'api/course-specfication';

  private enrolmentUrl = 'api/enrollment';

  constructor(private http: HttpClient,private authS:AuthenticationService) { }

  private RegenerateData = new Subject<void>();

  RegenerateData$ = this.RegenerateData.asObservable();

  announceChange() {
      this.RegenerateData.next();
  }

  getCoursesInstances(username:String,numberPage:number):Observable<HttpResponse<CourseInstance[]>> {
    var url = '';
    if(username!==''){
      url = `${this.coursesInstanceUrl}/teacher/${username}?sort=start_date,asc&page=${numberPage}&size=5`;
    }else if(this.authS.getRole()==='ROLE_ADMINISTRATOR'){
      url = `${this.coursesInstanceUrl}?sort=startDate,asc&page=${numberPage}&size=5`;
    }else if(this.authS.getRole()==='ROLE_TEACHER'){
      url = `${this.coursesInstanceUrl}/teacher?sort=start_date,asc&page=${numberPage}&size=5`;
    }else if(this.authS.getRole()==='ROLE_STUDENT'){
      url = `${this.coursesInstanceUrl}/student?sort=start_date,asc&page=${numberPage}&size=5`;
    }
    return this.http.get<CourseInstance[]>(url, {observe: 'response'});
  }

  getNumberPage(mode:string,username:string,courseId:number): Observable<HttpResponse<number>> {
    var url = `${this.coursesInstanceUrl}/number-course-instance?mode=${mode}&username=${username}`;
    if(mode === 'STUDENTS_COURSE'){
      url = `api/student/number-students?courseId=${courseId}`
    }else if(this.authS.getRole()==='ROLE_TEACHER'){
      var user = this.authS.getLoggedUser();
      username = JSON.stringify(user.sub).split('"')[1];
      mode = 'TEACHER';
      url = `${this.coursesInstanceUrl}/number-course-instance?mode=${mode}&username=${username}`;
    }else if(mode==="COURSE_SPECIFICATION"){
      url = `${this.coursesSpecificationUrl}/number-course-specification`
    }
    // console.log("Url: "+url)
    return this.http.get<number>(url, {observe: 'response'});
  }

  getCoursesSpecifications(numberPage:number,searchString:string):Observable<HttpResponse<CourseSpecification[]>> {
    var url = `${this.coursesSpecificationUrl}?searchString=${searchString}&sort=title,asc&page=${numberPage}&size=5`;
    if(numberPage==-1){
      url = `${this.coursesSpecificationUrl}?searchString=${searchString}&size=5`;
    }
    return this.http.get<CourseSpecification[]>(url, {observe: 'response'});
  }

  getCourseInstance(id: number): Observable<HttpResponse<CourseInstance>> {
    const url = `${this.coursesInstanceUrl}/${id}`;
    return this.http.get<CourseInstance>(url, {observe: 'response'});
  }

  getCourseSpecification(id: number): Observable<HttpResponse<CourseSpecification>> {
    const url = `${this.coursesSpecificationUrl}/${id}`;
    return this.http.get<CourseSpecification>(url, {observe: 'response'});
  }

  editCourseInstance(courseInstance: CourseInstance): Observable<HttpResponse<CourseInstance>> {
    return this.http.put<CourseInstance>(this.coursesInstanceUrl, courseInstance, {observe: 'response'});
  }

  editCourseSpecification(courseSpecification: CourseSpecification): Observable<HttpResponse<CourseSpecification>> {
    return this.http.put<CourseSpecification>(this.coursesSpecificationUrl, courseSpecification, {observe: 'response'});
  }

  addCourseInstance(courseInstance: CourseInstance): Observable<HttpResponse<CourseInstance>> {
      return this.http.post<CourseInstance>(this.coursesInstanceUrl, courseInstance, {observe: 'response'});
  }

  addCourseSpecification(courseSpecification: CourseSpecification): Observable<HttpResponse<CourseSpecification>> {
    return this.http.post<CourseSpecification>(this.coursesSpecificationUrl, courseSpecification, {observe: 'response'});
  }

  addEnrollment(enrolment: Enrollment): Observable<HttpResponse<Enrollment>> {
    return this.http.post<Enrollment>(this.enrolmentUrl, enrolment, {observe: 'response'});
  }

  deleteEnrollment(enrolment: Enrollment): Observable<HttpResponse<Enrollment>> {
    const url = `${this.enrolmentUrl}/${enrolment.courseInstanceDTO.id}/${enrolment.studentDTO.cardNumber}`
    return this.http.delete<Enrollment>(url, {observe: 'response'});
  }

  deleteCourseInstance(courseInstanceId: number): Observable<HttpResponse<any>> {
    const url = `${this.coursesInstanceUrl}/${courseInstanceId}`;
    return this.http.delete<any>(url, {observe: 'response'});
  }

  deleteCourseSpecification(courseSpecificationId: number): Observable<HttpResponse<any>> {
    const url = `${this.coursesSpecificationUrl}/${courseSpecificationId}`;
    return this.http.delete<any>(url, {observe: 'response'});
  }
}
