import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';

@Component({
  selector: 'app-add-checklist-textarea',
  templateUrl: './add-checklist-textarea.component.html',
  styleUrls: ['./add-checklist-textarea.component.css']
})
export class AddChecklistTextareaComponent implements OnInit {
  comment;
  emptyService: any;
  serviceData = [];
  serviceDatas: any;
  newServiceData = [];
  @Input() inputData: any;
  @Output() output = new EventEmitter<Object>();
  constructor() {
    console.log(this.inputData)
  }

  ngOnInit() {
    this.serviceData = [];

  }
  emitData() {
    //console.log("Blur")
    this.output.emit({
      "comment": this.comment,
      "subchecklist": this.serviceData,
    });
  }
  serviceOutput(event) {
    this.serviceDatas = event;
    console.log(event);
    this.serviceData.push(event.comment);
    var newArr = [],
      origLen = this.serviceData.length,
      found, x, y;

    for (x = 0; x < origLen; x++) {
      found = undefined;
      for (y = 0; y < newArr.length; y++) {
        if (this.serviceData[x] === newArr[y]) {
          found = true;
          break;
        }
      }
      if (!found) {
        if (origLen == 1) {
          console.log("in if 1 position")
          this.newServiceData = this.serviceData;
        }
        else {
          newArr.push(this.serviceData[x]);
        }
      }
      newArr = this.newServiceData;
    }
    this.output.emit({
      "comment": this.comment,
      "subchecklist": this.newServiceData,
    });
    //console.log(this.serviceData);
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
      document.getElementById('deleteServiceButton1').style.opacity = '1'
      document.getElementById('deleteServiceButton1').style.cursor = 'pointer';
    }
  }
  DeleteServices() {
    //this.sample.pop();
    if (this.sample.length > 1) {
      this.sample.pop();
    }
    if (this.sample.length === 1) {
      document.getElementById('deleteServiceButton1').style.opacity = '0.6'
      document.getElementById('deleteServiceButton1').style.cursor = 'not-allowed';
    }
  }
  sample: Array<number> = [1];

}
