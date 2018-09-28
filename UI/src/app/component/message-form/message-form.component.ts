import { Component, OnInit, Input } from '@angular/core';
import { Message } from '../../model';
import { ChatbotServiceService } from '../../service/chatbot-service.service';

@Component({
  selector: 'app-message-form',
  templateUrl: './message-form.component.html',
  styleUrls: ['./message-form.component.css']
})
export class MessageFormComponent implements OnInit {

  @Input('message')
  private message: Message;

  @Input('messages')
  private messages: Message[];

  constructor(private chatbotService: ChatbotServiceService) { }

  ngOnInit() {
  }

  public sendMessage(): void {
    this.message.timestamp = new Date();
    this.messages.push(this.message);

    this.chatbotService.getChatResponse(this.message.content).subscribe(res => {
      this.messages.push(
        new Message('bot', 'hi this is bot', 'assets/Images/bot.png', new Date())  // bot
      );
    },
    error => {
      console.log('error is in list', error.error.text);
      this.messages.push( new Message('bot', error.error.text, 'assets/Images/bot.png', new Date()));
     });
    this.message = new Message('user', '', 'assets/Images/user.png');  /// user
  }


}
