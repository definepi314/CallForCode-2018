import { CheckListService } from './../../service/check-list.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-tutorial-videos',
  templateUrl: './tutorial-videos.component.html',
  styleUrls: ['./tutorial-videos.component.css']
})
export class TutorialVideosComponent implements OnInit {
  srcPath: String;
  videoFlag: boolean = false;
  username: string;
  tutorialArray = []
  videos = [{
    name: 'video1',
    path: 'https://d3c33hcgiwev3.cloudfront.net/yaSMp3pSEeam4BLcQYZr8Q.processed/full/360p/index.webm?Expires=1536451200&Signature=f4I0~J4R-CJuaSCOBM9yrQD3Bwbm-a~Jk~SxcEmEFYXXtvDc6E-94iEN7EkGwL8mIIS7FW9JhnWuw9ZsM6wO7wQGlV3JT1ZIREu7hCFVWOsIucMpMrVDjY~7okltfIaVPoqzi~P-nsf9LUmJJQu0As9uEUVZ1VZbpHcRU-Ed-as_&Key-Pair-Id=APKAJLTNE6QMUY6HBC5A'
  },
  {
    name: 'video2',
    path: 'https://youtu.be/WgktM2luLok'
  },
  ]
  constructor(public checkList: CheckListService) { }

  ngOnInit() {
    this.username = localStorage.getItem("Username");
    console.log(this.username);
    this.checkList.getTutorial(this.username).subscribe((response) => {
      this.tutorialArray = response['response']['checklists'];
      console.log('Tutorials are', this.tutorialArray);
    });
  }
  videoLink(video) {
     console.log(video.videoLink);
    this.videoFlag = true
    this.srcPath = video.videoLink;
  }
}
