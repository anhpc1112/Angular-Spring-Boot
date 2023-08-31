import { Component } from '@angular/core';
import { faFacebookMessenger } from '@fortawesome/free-brands-svg-icons';
import {
  faBell,
  faHouse,
  faListUl,
  faMoon,
  faStore,
  faTv,
  faUsers,
} from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css'],
})
export class NavComponent {
  faHouse = faHouse;
  faTv = faTv;
  faStore = faStore;
  faUsers = faUsers;
  faListUl = faListUl;
  faFacebookMessenger = faFacebookMessenger;
  faBell = faBell;
  faMoon = faMoon;
}
