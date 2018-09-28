import { Component, OnInit } from '@angular/core';
import { trigger, style, animate, transition } from '@angular/animations';
import { Message } from '../../model';
import { ChatbotServiceService } from '../../service/chatbot-service.service';

@Component({
  selector: 'app-render-component',
  templateUrl: './render-component.component.html',
  styleUrls: ['./render-component.component.css']
})
export class RenderComponentComponent implements OnInit {

  public message: Message;
  public messages: Message[];


  constructor(private chatService: ChatbotServiceService) {
  }

  ngOnInit() {
    this.message = new Message('user', '', 'assets/Images/user.png');  // user
    this.chatService.getChatResponse(null).subscribe(response => {
      console.log('chat response is', response);
    },
    error => {
     console.log('error is', error.error.text);
     this.messages = [
      new Message('bot', error.error.text, 'assets/Images/bot.png', new Date())  // bot
    ];
    });
  }
}
