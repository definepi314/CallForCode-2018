import { Subject } from 'rxjs';
import { ToastrService } from 'ngx-toastr';
import { DisasterTrainingService } from './../../service/disaster-training.service';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';

export class disaster {
  id: number;
  name: string;
}

@Component({
  selector: 'app-add-checklist',
  templateUrl: './add-checklist.component.html',
  styleUrls: ['./add-checklist.component.css']
})

export class AddChecklistComponent implements OnInit {
  tutorialType: any;
  disasterId: any;
  checkListTemp: any;
  checkLists: disaster[];
  serviceDatas: any;
  serviceData = [];
  emptyService: any;
  appDisasterForm: FormGroup;
  tutorialFlag: boolean = false;
  checklistFlag: boolean = false;
  subCheckList: any;
  finalChecklist: any = [];
  newServiceData = [];
  checkTypes = [
    { value: 'checklist', viewValue: 'CheckList' },
    { value: 'tutorial', viewValue: 'Tutorial' },
  ]
  constructor(public disasterTrainService: DisasterTrainingService, private formBuilder: FormBuilder, private toastr: ToastrService) { }

  ngOnInit() {
    // this.serviceData = []
    this.disasterTrainService.lookupDisaster().subscribe((res: disaster[]) => {
      console.log(res);
      this.checkLists = res;
    })
    this.appDisasterForm = this.formBuilder.group({
      disasterType: '',
      tutorialType: '',
      description: ''
    })
  }
  changeList(event) {
    console.log(event.value.id);
    this.disasterId = event.value.id;
  }
  serviceOutput(event) {
    console.log(event)
    this.serviceDatas = event;
    console.log(event.subchecklist);
    if (this.tutorialType === "checklist") {
      this.serviceData = [];
      console.log("event", event.comment, event.subchecklist)
      this.serviceData = event.comment;
      this.subCheckList = event.subchecklist;
    }
    if (this.tutorialType === "tutorial") {
      this.serviceData.push(event);
      // var newArr = [],
      // origLen = this.serviceData.length,
      // found, x, y;
      // for (x = 0; x < origLen; x++) {
      //   found = undefined;
      //   for (y = 0; y < newArr.length; y++) {
      //     if (this.serviceData[x] === newArr[y]) {
      //       found = true;
      //       break;
      //     }
      //   }
      //   if (!found) {
      //    // console.log("in if",origLen)
      //     if(origLen==1)
      //     {
      //       //console.log("in if 1 position")
      //       this.newServiceData=this.serviceData;
      //     }
      //     else{
      //       newArr.push(this.serviceData[x]);
      //     }
          
      //   }
      //   else{
      //     console.log("in else")
      //   }
      //   newArr = this.newServiceData;
      //   console.log("$$$$$$$",this.newServiceData)
      // }
      
    }
    console.log("check", this.serviceData, this.subCheckList);

  }
  changeType(event) {
    console.log(event.value);
    this.tutorialType = event.value
    if (event.value == "checklist") {
      this.checklistFlag = true;
      this.tutorialFlag = false;
    }
    if (event.value == "tutorial") {
      this.tutorialFlag = true;
      this.checklistFlag = false;
    }
  }

  addMoreServices() {
    console.log(this.sample.length);
    this.emptyService = {
      "comments": '',
    }
    this.sample.push(this.emptyService.comments);
    console.log(this.sample)

    // if (this.serviceDatas.seviceName !== undefined && this.serviceDatas.servicePlan !== undefined && this.serviceDatas.serviceType !== undefined) {
    //   //console.log('Hi');
    //   this.sample.push(this.emptyService);
    // }
    if (this.sample.length > 1) {
      document.getElementById('deleteServiceButton').style.opacity = '1'
      document.getElementById('deleteServiceButton').style.cursor = 'pointer';
    }
  }
  DeleteServices() {
    //this.sample.pop();
    if (this.sample.length > 1) {
      this.sample.pop();
    }
    if (this.sample.length === 1) {
      document.getElementById('deleteServiceButton').style.opacity = '0.6'
      document.getElementById('deleteServiceButton').style.cursor = 'not-allowed';
    }
  }
  sample: Array<number> = [1];

  submitDisaster(event) {
    console.log(this.tutorialType);
    if (this.tutorialType === "checklist") {
      console.log(this.serviceData, this.subCheckList);
      let disasterBody = {
        "disasterId": this.disasterId,
        "checkList": this.serviceData,
        "description": event.description,
        "subchecklist": this.subCheckList
      }
      console.log(disasterBody);
      this.disasterTrainService.addCheckList(disasterBody).subscribe(res => {
        console.log(res);
        if (res['response'] === true) {
          this.toastr.success('', 'Checklist Saved Successfully');
        }
      },
        err => {
          console.log(err);
          this.toastr.error('', 'Checklist does not Saved');
        })
    }
    if (this.tutorialType === "tutorial") {
      let disasterBody = {
        "disasterId": this.disasterId,
        // "trainingType": event.tutorialType,
        "checklists": this.serviceData
      }
      console.log(disasterBody);
      this.disasterTrainService.addTutorial(disasterBody).subscribe(res => {
        if (res["response"] === true) {
          this.toastr.success('', 'Checklist Saved Successfully');
        }
      },
        err => {
          console.log(err);
          this.toastr.error('', 'Checklist does not Saved');
        })
    }

  }
  // reset() {
  //   this.appDisasterForm.reset();
  //   this.serviceData=[];
  // }
}
