import { Location } from '@angular/common';
import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { switchMap } from 'rxjs/operators';
import { Account } from 'src/app/model/accounts';
import { Payment } from 'src/app/model/payment';
import { Student } from 'src/app/model/student';
import { User } from 'src/app/model/user';
import { PaymentService } from '../payment/payment.service';
import { StudentDetailComponent } from '../student-detail/student-detail.component';

@Component({
  selector: 'app-add-payment',
  templateUrl: './add-payment.component.html',
  styleUrls: ['./add-payment.component.css']
})
export class AddPaymentComponent implements OnInit {

  payment: Payment;
  username = '';
  mode = '';
  date = '';

  constructor(
    private paymentService:PaymentService,
    private location: Location,
    private route: ActivatedRoute) {
      this.payment = new Payment(
        {
          id:0,
          currency:'',
          amount:0,
          date:new Date(),
          urgently:false,
          note:'',
          accountDTO:new Account({
            id:0,
            amount:0,
            studentDTO:new Student({
              id:0,
              cardNumber:"",
              userDTO:new User({
                id:0,
                firstName:"",
                lastName:"",
                userName:"",
                password:"",
                roles:[],
              })
            }),
            payments:[]
          })
        }
      )
    this.mode = 'ADD';
    }

  ngOnInit(): void {
    if(this.route.snapshot.params['studentUsername']){
      this.username = this.route.snapshot.params.studentUsername;
    }else if (this.route.snapshot.params['id']) {
      this.mode = 'EDIT';
      this.route.params.pipe(switchMap((params: Params) => 
        this.paymentService.getPayment(+params['id']))) // convert to number
        .subscribe(res => {
          this.payment = res.body==null ? this.payment:res.body;
          this.date = new Date(this.payment.date).toISOString().substring(0, 10);
        });
    }
  }

  addPayment(){
    this.paymentService.addAccountPayment(this.payment,this.username).subscribe(() => {this.goBack() });
  }

  editPayment(){
    this.payment.date = new Date(this.date);
    this.paymentService.editPayment(this.payment).subscribe(() => {this.goBack() });
  }

  goBack(): void {
    this.location.back();
  }
}
