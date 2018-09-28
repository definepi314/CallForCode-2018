import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-add-checklist-textarea1',
  templateUrl: './add-checklist-textarea1.component.html',
  styleUrls: ['./add-checklist-textarea1.component.css']
})
export class AddChecklistTextarea1Component implements OnInit {
  @Output() output = new EventEmitter<Object>();
  name;
  Link;
  isDisable: Boolean = true;
  TutorialName: FormControl = new FormControl();
  TutorialLink: FormControl = new FormControl();
  tutorialOptions: Observable<string[]>;

  constructor() { }

  ngOnInit() {
   
    this.TutorialLink.valueChanges.subscribe(y => {
      //console.log(y);
      if(y!==undefined)
      {
        if (y.length >= 6 && this.TutorialName.value !== null && this.TutorialName.value !== "") {
          this.isDisable = false;
        } else {
          this.isDisable = true;
        }
      }
      
    });

    this.TutorialName.valueChanges.subscribe(x => {
     // console.log(x);
      if(x!==undefined){
        if (this.TutorialLink.value !== null && this.TutorialLink.value !== "") {
          if (this.TutorialLink.value.length >= 6 && this.TutorialLink.value !== null && this.TutorialLink.value !== "") {
            this.isDisable = false;
            this.TutorialLink = new FormControl({ value: null, disabled: false });
          } else {
            this.isDisable = true;
          }
        } else {
          this.isDisable = true;
        }
      }
      

    });
  }
  saveTutorial(tutorialName,tutorialLink) {
    if (this.TutorialName.value !== null && this.TutorialLink.value !== null) {
      this.isDisable = true;
    }
    console.log(tutorialName,tutorialLink);
    let jsonObject={
      "videoName":tutorialName,
      "videoLink":tutorialLink
    }
    this.output.emit(jsonObject);
  }
}
