export class Message {
    sender: string;
    content: string;
    timestamp: Date;
    avatar: string;
    constructor(sender: string, content: string, avatar: string, timestamp?: Date) {
      this.sender = sender;
      this.content = content;
      this.timestamp = timestamp;
      this.avatar = avatar;
    }
  }
