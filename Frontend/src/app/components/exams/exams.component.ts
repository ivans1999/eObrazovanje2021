import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Subscription } from 'rxjs';
import { switchMap } from 'rxjs/operators';
import { AppComponent } from 'src/app/app.component';
import { Exam } from 'src/app/model/exam';
import { User } from 'src/app/model/user';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { ExamsService } from './exams.service';

@Component({
  selector: 'app-exams',
  templateUrl: './exams.component.html',
  styleUrls: ['./exams.component.css']
})
export class ExamsComponent implements OnInit {

  // user: string | null = localStorage.getItem('loggedUser');
  exams: Exam[] = [];

  public role: string = '';
  subscription: Subscription;
  numberPages:number[] = [];
  numberPage:number = 0;

  constructor(private examService:ExamsService, private router: Router, private route: ActivatedRoute,private authenticationService: AuthenticationService) {
    this.role = this.authenticationService.getRole();
    this.subscription = examService.RegenerateData$.subscribe(() =>
    this.getExams());
  }

  ngOnInit(): void {
    this.getExams();
    
  }

  getExams(){
    this.examService.getExams(this.role,this.numberPage).subscribe(
      response => {
        this.exams = response.body == null ? this.exams:response.body;
        this.getNumberPages();
      });
  }

  getNumberPages(){
    this.examService.getNumberPage().subscribe(res =>{
      const num = res.body == null ? 0:res.body;
      var i = 1;
      this.numberPages = [];
      for (let index = 0; index < num; index++) {
        this.numberPages.push(i);
        i++;
      }
    })
  }

  gotToViewExam(exam: Exam):void{
    this.router.navigate(['/exam-detail/student/', exam.id]);
  }

  increaseNumberPage(){
    if(this.numberPage < this.numberPages.length-1){
      this.numberPage=this.numberPage+1;
      this.getExams();
    }
  }

  reduceNumberPage(){
    if(this.numberPage>=1){
      this.numberPage=this.numberPage-1;
      this.getExams();
    }
  }

  setNumberPage(numberPage:number){
    this.numberPage = numberPage-1;
    this.getExams();
  }
}
