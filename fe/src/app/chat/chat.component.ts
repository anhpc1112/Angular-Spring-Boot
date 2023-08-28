import { Component, OnDestroy, OnInit } from '@angular/core';
import { ChatMessage } from './ChatMessage';
import { RxStompService } from '../rx-stomp.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css'],
})
export class ChatComponent implements OnInit, OnDestroy {
  messages: ChatMessage[] = [];
  newMessage: string = '';
  // @ts-ignore, to suppress warning related to being undefined
  private topicSubscription: Subscription;

  constructor(private rxStompService: RxStompService) {}

  ngOnInit(): void {
    this.rxStompService.watch('/topic/public').subscribe((message) => {
      this.messages.push(JSON.parse(message.body) as ChatMessage);
    });
  }

  ngOnDestroy() {
    this.topicSubscription.unsubscribe();
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
