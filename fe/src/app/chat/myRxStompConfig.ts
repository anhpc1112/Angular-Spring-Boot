import { InjectableRxStompConfig } from '@stomp/ng2-stompjs';

export const myRxStompConfig: InjectableRxStompConfig = {
  brokerURL: 'ws://localhost:8080/api/v1/ws', // Địa chỉ WebSocket server
  // ...các tùy chọn khác...
};
