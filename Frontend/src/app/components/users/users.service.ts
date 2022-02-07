import { Injectable } from '@angular/core';
import { HttpResponse, HttpClient, HttpHeaders } from '@angular/common/http';
import {Observable, Subject} from 'rxjs';

import { User } from '../../model/user';
import { LoginData } from 'src/app/model/loginData';
import { JWT } from 'src/app/model/jwt';
import { Role } from 'src/app/model/role';
import { CourseInstance } from 'src/app/model/courseInstance';
import { Student } from 'src/app/model/student';
import { Teacher } from 'src/app/model/teacher';
import { ChangePass } from 'src/app/model/changePass';

@Injectable()
export class UserService {
    private usersUrl = 'api/users';
    private loginUrl = 'api/login';
    private signUp = 'api/signup';
    private studentURL = 'api/student'
    private teacherUrl = 'api/teacher'

    constructor(private http: HttpClient) { }

    private RegenerateData = new Subject<void>();

    RegenerateData$ = this.RegenerateData.asObservable();

    announceChange() {
        this.RegenerateData.next();
    }

    getUsers(numberPage:number): Observable<HttpResponse<User[]>> {
        const url = `${this.usersUrl}?sort=firstName,asc&page=${numberPage}&size=5`
        return this.http.get<User[]>(url, {observe: 'response'});
    }

    getNumberPage(mode:string): Observable<HttpResponse<number>> {
        var url = `api/number-users?mode=${mode}`
        return this.http.get<number>(url, {observe: 'response'});
    }

    getTeachers(numberPage:number,searchString:string): Observable<HttpResponse<Teacher[]>> {
        var url = `${this.teacherUrl}?searchString=${searchString}&page=${numberPage}&size=5`;
        if(numberPage==-1){
            url = `${this.teacherUrl}?searchString=${searchString}&size=5`;
        }
        return this.http.get<Teacher[]>(url, {observe: 'response'});
    }

    getCourseInstanceStudents(courseInstance:CourseInstance,numberPage:number): Observable<HttpResponse<Student[]>> {
        const url = `${this.studentURL}/course-instance/${courseInstance.id}?page=${numberPage}&size=5`
        return this.http.get<Student[]>(url, {observe: 'response'});
    }

    getCourseInstanceTeacher(courseInstance:CourseInstance,searchString:string): Observable<HttpResponse<Teacher>> {
        console.log("getCourseInstanceTeacher!")
        const url = `${this.teacherUrl}/course-instance/${courseInstance.id}`
        return this.http.get<Teacher>(url, {observe: 'response'});
    }

    getCourseInstanceOtherStudents(courseInstance:CourseInstance,searchString:string): Observable<HttpResponse<Student[]>> {
        const url = `${this.studentURL}/course-instance/other-students/${courseInstance.id}?searchString=${searchString}&size=5`
        return this.http.get<Student[]>(url, {observe: 'response'});
    }

    login(loginData: LoginData): Observable<HttpResponse<JWT>> {
        return this.http.post<JWT>(this.loginUrl, loginData, {observe: 'response'});
    }

    getUser(id: number): Observable<HttpResponse<User>> {
        const url = `${this.usersUrl}/${id}`;
        return this.http.get<User>(url, {observe: 'response'});
    }

    getTeacher(id: number): Observable<HttpResponse<Teacher>> {
        const url = `${this.teacherUrl}/${id}`;
        return this.http.get<Teacher>(url, {observe: 'response'});
    }

    getStudent(id: number): Observable<HttpResponse<Student>> {
        const url = `${this.studentURL}/${id}`;
        return this.http.get<Student>(url, {observe: 'response'});
    }

    getLoggedUser(): Observable<HttpResponse<User>> {
        const url = `${this.usersUrl}/loggedUser`;
        return this.http.get<User>(url, {observe: 'response'});
    }

    getUnassignedRoles(username: string): Observable<HttpResponse<Role[]>> {
        const url = `${this.usersUrl}/${username}/unassigned-roles`;
        return this.http.get<Role[]>(url, {observe: 'response'});
    }

    editUser(user: User): Observable<HttpResponse<User>> {
        return this.http.put<User>(this.usersUrl, user, {observe: 'response'});
    }

    addUser(user: User): Observable<HttpResponse<User>> {
        return this.http.post<User>(this.signUp, user, {observe: 'response'});
    }

    deleteUser(userId: number): Observable<HttpResponse<any>> {
        const url = `${this.usersUrl}/${userId}`;
        return this.http.delete<any>(url, {observe: 'response'});
    }

    deleteTeacher(stId: number): Observable<HttpResponse<any>>{
        const url = `${this.teacherUrl}/${stId}`;
        return this.http.delete<any>(url, {observe:'response'});
    }

    changePass(changePass: ChangePass): Observable<HttpResponse<User>>{
        const url = `api/change-password`;
        return this.http.post<User>(url, changePass, {observe: 'response'});
    }
    // getUserRoles(userId: number): Observable<HttpResponse<Role[]>> {
    //     const url = `${this.usersUrl}/${userId}/role`;
    //     return this.http.get<Role[]>(url, {observe: 'response'});
    // } 
}