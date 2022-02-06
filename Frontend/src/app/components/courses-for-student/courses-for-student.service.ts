import { HttpClient, HttpResponse } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, Subject } from "rxjs";
import { CourseInstance } from "src/app/model/courseInstance";
import { AuthenticationService } from "src/app/services/authentication.service";

@Injectable()
export class CourseInstanceForStudentService {
    private courseInstancesForStudent = 'api/course-instance';

    
    constructor(private http: HttpClient,private authS:AuthenticationService) { }

    private RegenerateData = new Subject<void>();

    RegenerateData$ = this.RegenerateData.asObservable();

    announceChange() {
        this.RegenerateData.next();
    }

    getStudentCourses(username: String): Observable<HttpResponse<CourseInstance[]>>{
        var url = `${this.courseInstancesForStudent}/all/for-student/${username}`;
        return this.http.get<CourseInstance[]>(url, {observe: 'response'});
    }

    getCoursesInstances(username:String,numberPage:number):Observable<HttpResponse<CourseInstance[]>> {
        var url = '';
        // console.log(username + " usernameeeee");
        if(username!==''){
          url = `${this.courseInstancesForStudent}/all/for-student/${username}?sort=start_date,asc&page=${numberPage}&size=5`;
        }else if(this.authS.getRole()==='ROLE_ADMINISTRATOR'){
          url = `${this.courseInstancesForStudent}?sort=startDate,asc&page=${numberPage}&size=5`;
        }else if(this.authS.getRole()==='ROLE_TEACHER'){
          url = `${this.courseInstancesForStudent}/teacher?sort=start_date,asc&page=${numberPage}&size=5`;
        }else if(this.authS.getRole()==='ROLE_STUDENT'){
          url = `${this.courseInstancesForStudent}/all/for-student?sort=start_date,asc&page=${numberPage}&size=5`;
        }
        return this.http.get<CourseInstance[]>(url, {observe: 'response'});
      }

      getNumberPage(mode:string,username:string,courseId:number): Observable<HttpResponse<number>> {
        var url = `${this.courseInstancesForStudent}/number-course-instance?mode=${mode}&username=${username}`
        if(mode === 'STUDENTS_COURSE'){
          url = `api/student/number-students?courseId=${courseId}`
        }else if(this.authS.getRole()==='ROLE_TEACHER'){
          var user = this.authS.getLoggedUser();
          username = JSON.stringify(user.sub).split('"')[1];
          mode = 'TEACHER';
        }else if(mode==="COURSE_SPECIFICATION"){
          url = `${this.courseInstancesForStudent}/number-course-specification`
        }
        else if(this.authS.getRole()==='ROLE_STUDENT'){
            var user = this.authS.getLoggedUser();
            username = JSON.stringify(user.sub).split('"')[1];
            mode = 'STUDENT';
          }
        // console.log("Url: "+url)
        return this.http.get<number>(url, {observe: 'response'});
      }
}