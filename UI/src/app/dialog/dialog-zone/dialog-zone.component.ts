import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef, MatDialog } from "@angular/material";

@Component({
  selector: 'app-dialog-zone',
  templateUrl: './dialog-zone.component.html',
  styleUrls: ['./dialog-zone.component.css']
})
export class DialogZOneComponent implements OnInit {

  constructor(private dialog: MatDialog,private dialogRef: MatDialogRef<DialogZOneComponent>, @Inject(MAT_DIALOG_DATA) public data: any) { }

  ngOnInit() {
  }

  close() {
    this.dialogRef.close();
  }

}
