
import { Component, OnInit, Input, ViewChild, ElementRef, Renderer2 } from '@angular/core';
import { Message } from '../../model';

@Component({
  selector: 'app-message-item',
  templateUrl: './message-item.component.html',
  styleUrls: ['./message-item.component.css']
})
export class MessageItemComponent implements OnInit {

  @ViewChild('listGroup') listGroup: ElementRef;
  @ViewChild('img') img: ElementRef;
  @Input('message')
  private message: Message;

  constructor(private renderer: Renderer2) {
  }

  ngOnInit() {
    if (this.message.sender === 'user') {
      // this.listGroup.nativeElement.style.backgroundColor = 'blue';
      this.renderer.setStyle(this.listGroup.nativeElement, 'backgroundColor', '#b9dcc7');
    } else {
      this.renderer.addClass(this.img.nativeElement, 'float-right');
    }
  }

}
