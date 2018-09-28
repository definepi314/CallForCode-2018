import { Component, OnInit ,ViewChild} from '@angular/core';
import { MatDialog, MatDialogConfig, MAT_DIALOG_DATA, MatDialogRef } from "@angular/material";
import { DialogZOneComponent } from '../dialog/dialog-zone/dialog-zone.component';

// declare var google: any;
@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  constructor(private dialog: MatDialog) { }

  ngOnInit() {
  }

}
