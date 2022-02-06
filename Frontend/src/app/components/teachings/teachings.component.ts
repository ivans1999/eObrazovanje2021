import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { switchMap } from 'rxjs/operators';
import { Teaching } from 'src/app/model/teaching';
import { User } from 'src/app/model/user';
import { TeachingsService } from './teachings.service';

@Component({
  selector: 'app-teachings',
  templateUrl: './teachings.component.html',
  styleUrls: ['./teachings.component.css']
})
export class TeachingsComponent implements OnInit {

  user: User = { id:0, firstName:"", lastName:"", userName:"",password:"", roles:[]};
  teachings: Teaching[] | null= [];

  subscription: Subscription;


  constructor(private teachingService: TeachingsService, private router: Router, private route: ActivatedRoute) { 
    this.subscription = teachingService.RegenerateData$.subscribe(() =>
    this.getTeachings());
  }

  ngOnInit(): void {
      this.teachingService.getTeachings()
      .subscribe(res => {
        this.teachings = res.body;
      });
  }

  getTeachings(){
    this.teachingService.getTeachings().subscribe(
      response => {
        this.teachings = response.body
      }
    );
  }

  goToViewUser(user: User): void {
    this.router.navigate(['/view-user', user.id]);
  }


}
