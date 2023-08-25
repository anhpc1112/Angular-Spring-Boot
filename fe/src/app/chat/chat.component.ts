import { Component } from '@angular/core';
import { ChatMessage } from './ChatMessage';
import { RxStompService } from '@stomp/ng2-stompjs';
import { myRxStompConfig } from './myRxStompConfig';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css'],
})
export class ChatComponent {
  messages: ChatMessage[] = [
    {
      content: 'abc',
    },
    {
      content: 'xyz',
    },
  ];
  newMessage: string = '';

  constructor(private rxStompService: RxStompService) {}

  ngOnInit(): void {
    this.rxStompService.configure(myRxStompConfig);
    this.rxStompService.activate();

    this.rxStompService.watch('/topic/public').subscribe((message) => {
      this.messages.push(JSON.parse(message.body) as ChatMessage);
    });
  }

  sendMessage() {
    const message: ChatMessage = { content: this.newMessage };
    console.log('message: ', message);
    this.rxStompService.publish({
      destination: '/app/chat.sendMessage',
      body: JSON.stringify(message),
    });
    this.newMessage = '';
  }
}
