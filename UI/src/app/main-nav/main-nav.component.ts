import { Component, OnInit, OnChanges } from '@angular/core';
import { Router } from '@angular/router';
import { BreakpointObserver, Breakpoints, BreakpointState } from '@angular/cdk/layout';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { DataService } from '../service/data.service';
import {
  trigger,
  state,
  style,
  animate,
  transition
} from '@angular/animations';
import { not } from '@angular/compiler/src/output/output_ast';

@Component({
  selector: 'app-main-nav',
  templateUrl: './main-nav.component.html',
  styleUrls: ['./main-nav.component.css'],
  animations: [
    trigger('popOverState', [
      state('true', style({
        opacity: 1,
        //transform: 'rotateY(360deg) rotateZ(360deg)',
        transform: 'translateX(-5%) translateY(-5%)'
      })),
      state('false', style({
        opacity: 0,

      })),
      transition('true => false', animate('500ms ease-out')),
      transition('false => true', animate('500ms ease-in'))
    ])
  ]
})
export class MainNavComponent implements OnInit {
  userRole: string;
  firstName: any;
  lastName: any;
  renderFlag: boolean = false;
  show = false;
  chatBoatFlag: boolean = true;
  zoneMarkFlag: boolean = false;
  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
    map(result => result.matches)
    );

  constructor(private router: Router, private breakpointObserver: BreakpointObserver) {
  }
  LogOut() {

  }

  ngOnInit() {
    this.userRole = localStorage.getItem("Role");
    console.log(this.userRole);
    if (this.userRole === "general") {
      this.zoneMarkFlag = false
    }
    if (this.userRole === "Admin") {
      this.zoneMarkFlag = true
    }
    this.renderFlag = false;
    this.router.navigate(['/dashboard/home']);
  }
  get stateName() {
    return this.renderFlag ? 'true' : 'false'
  }
  renderEvent() {
    this.renderFlag = !this.renderFlag;
  }
}
