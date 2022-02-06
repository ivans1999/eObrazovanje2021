import { Location } from '@angular/common';
import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { Payment } from 'src/app/model/payment';
import { Student } from 'src/app/model/student';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { StudentDetailComponent } from '../student-detail/student-detail.component';
import { PaymentService } from './payment.service';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent implements OnInit {

  payments: Payment[] | null = [];

  numberPages:number[] = [];
  numberPage:number = 0;
  mode = "";
  // subscription: Subscription;

  @Input() student:Student = new Student({
    id:0,
    cardNumber: '',
    userDTO:{
      id:0,
      firstName:'',
      lastName:'',
      userName:'',
      password:'',
      roles:[]
    }
  })
  role = '';
  subscription:Subscription;
  
  constructor(private paymentService: PaymentService, 
              private auths: AuthenticationService,
              private router: Router,
              private studentDetail: StudentDetailComponent,
              private location: Location
              ) {
    if(auths.getRole() === 'ROLE_ADMINISTRATOR'){
      this.student = studentDetail.student;
      this.role = 'ROLE_ADMINISTRATOR';
    }
    this.subscription = paymentService.RegenerateData$.subscribe(() =>
      // this.getPayments()
      this.getStudentPayments());
  }

  ngOnInit(): void {
    this.getStudentPayments();
  }

  getPayments(){
    this.paymentService.getAccountPayments(0).subscribe(
      response => {
        this.payments = response.body;
      }
    )
  }

  getStudentPayments(){
    this.getNumberPages();
    this.paymentService.getStudentPayments(this.student.userDTO.userName, this.numberPage).subscribe(
      response => {
        this.payments = response.body;
      }
    )
  }

  getNumberPages(){
    this.paymentService.getNumberPage2(this.student.userDTO.userName).subscribe(res =>{
      const num = res.body == null ? 0:res.body;
      var i = 1;
      this.numberPages = [];
      for (let index = 0; index < num; index++) {
        this.numberPages.push(i);
        i++;
      }
    })
  }

  goToPayment(payment: Payment): void {
    this.router.navigate(['/payment', payment.id]);
  }

  goToAddPayment():void{
    this.router.navigate(['/admin-add-payment/', this.student.userDTO.userName]);
  }

  increaseNumberPage(){
    if(this.numberPage < this.numberPages.length-1){
      this.numberPage=this.numberPage+1;
      this.getStudentPayments();
    }
  }

  setNumberPage(numberPage:number){
    this.numberPage = numberPage-1;
    this.getStudentPayments();
  }

  reduceNumberPage(){
    if(this.numberPage>=1){
      this.numberPage=this.numberPage-1;
      this.getStudentPayments();
    }
  }

  isActive(num:number):boolean{
    if(this.numberPage===num){
      return true;
    }
    return false;
  }

  dateToString(date:Date):Date{
    var d = new Date(date);
    d.setHours(d.getHours()-1);
    return d;
  }

  goBack(): void {
    this.location.back();
  }
}
