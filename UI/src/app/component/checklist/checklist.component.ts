import { DataService } from './../../service/data.service';
import { MatDialogConfig } from '@angular/material';
import { Component, OnInit, Inject } from '@angular/core';
import { CheckListService } from '../../service/check-list.service';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { FillSubChecklistComponent } from '../fill-sub-checklist/fill-sub-checklist.component';

@Component({
  selector: 'app-checklist',
  templateUrl: './checklist.component.html',
  styleUrls: ['./checklist.component.css']
})
export class ChecklistComponent implements OnInit {
  checkListArray: Array<Object>;
  username: string
  constructor(private checkList: CheckListService, private dialog: MatDialog, public dataService: DataService) { }

  ngOnInit() {
    this.username = localStorage.getItem("Username");
    console.log(this.username);
    this.checkList.getCheckList(this.username).subscribe((response) => {
      this.checkListArray = response['response'];
      console.log('checklist are', this.checkListArray);
    });
    this.dataService.currentSubCheckList.subscribe(res => {
      if (res["response"] === true) {
        this.checkList.getCheckList(this.username).subscribe((response) => {
          this.checkListArray = response['response'];
          console.log('checklist are', this.checkListArray);
        });
      }
    })
  }

  goToFillChekList(eventData) {
    // const dialogConfig = new MatDialogConfig();
    // dialogConfig.data = {
    //   id: 1,
    //   title: 'Angular For Beginners'
    // };
    const dialogRef = this.dialog.open(FillSubChecklistComponent, {
      width: '700px',
      data: eventData
    });
    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed', result);
    });
  }

}
