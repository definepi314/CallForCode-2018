import { DataService } from './../../service/data.service';
import { ToastrService } from 'ngx-toastr';
import { CheckListService } from './../../service/check-list.service';
import { Component, Inject, OnInit } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA, MatRadioChange } from '@angular/material';

@Component({
  selector: 'app-fill-sub-checklist',
  templateUrl: './fill-sub-checklist.component.html',
  styleUrls: ['./fill-sub-checklist.component.css']
})
export class FillSubChecklistComponent implements OnInit {
  checkListObjects: any;
  userName: string;
  radioClick: String;

  header: String;
  subheader: String;
  subCheckList: Array<String>;
  radioObjects: Array<String>;
  options: Array<String>;
  output: any;
  subCheklistArray = [];
  constructor(
    public dialogRef: MatDialogRef<FillSubChecklistComponent>,
    @Inject(MAT_DIALOG_DATA) public checkListObject, public checkListService: CheckListService, public toaster: ToastrService,
    public dataService: DataService) {
    console.log(checkListObject);
    this.checkListObjects=checkListObject;
    // console.log(this.subCheklistArray);
  }

  ngOnInit() {
    this.header = this.checkListObjects.checklist;
    this.subheader = this.checkListObjects.description;
    this.subCheckList = this.checkListObjects.filled;
    this.radioObjects = new Array(this.subCheckList.length);
    this.options = ['Yes', 'No'];
    for (let i = 0, j = 0; i < this.radioObjects.length, j < this.subCheckList.length; i++ , j++) {
      console.log(this.radioObjects[i])
      this.radioObjects[i] = this.subCheckList[j]['value'];
      this.radioClick = this.radioObjects[i];
    }
    console.log('data is', this.subCheckList);
  }
  onCloseClick(): void {
    this.output = { clicked: 'close', data: '' };
    this.dialogRef.close(this.output);
  }
  onSubmitClick() {
    this.output = { clicked: 'submit', data: this.radioObjects };
    this.dialogRef.close(this.output.data);
    //this.dataService.setSubCheckList(this.output.data)
    for (let i = 0, j = 0; i < this.output.data.length, j < this.subCheckList.length; i++ , j++) {
      let subChecklistBody = {
        "subCheckListId": this.subCheckList[j]['subCheckListId'],
        "name": this.subCheckList[j]['name'],
        "value": this.output.data[j]
      }
      this.subCheklistArray.push(subChecklistBody);
    }
    console.log(this.subCheklistArray, "$$$$$$$$$")

    this.userName = localStorage.getItem("Username");
    let checklistBody = {
      // "checkListId": 42,
      "userName": this.userName,
      "subCheckLists": this.subCheklistArray
    }
    this.checkListService.saveCheckList(checklistBody).subscribe(res => {
      console.log(res, checklistBody);
      this.dataService.setSubCheckList(res);
      if (res["response"] === true) {
        this.toaster.success('', 'SubChecklist Saved Successfully');
      }
    }, err => {
      // console.log(err);
      this.toaster.error('', 'SubChecklist does not Saved');
    })

  }
  radioChange(event: MatRadioChange) {
    //console.log(event.value);
    //this.radioClick=event.value
  }
}
