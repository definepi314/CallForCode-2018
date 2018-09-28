import { Component, OnInit, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-add-subchecklist-textarea',
  templateUrl: './add-subchecklist-textarea.component.html',
  styleUrls: ['./add-subchecklist-textarea.component.css']
})
export class AddSubchecklistTextareaComponent implements OnInit {
  comment;
  @Output() output = new EventEmitter<Object>();
  
  constructor() { }
 
  ngOnInit() {
  }
  emitData() {
    //console.log("Blur")
    this.output.emit({
      "comment": this.comment,
    });
  }
}
