import { Component, EventEmitter, Input, OnDestroy, OnInit, Output } from '@angular/core';
import { userMenuItem } from './userMenuItem';
import { SecurityService } from 'src/app/security/security.service';
import { Subscription } from 'rxjs';
import { User } from 'src/app/interfaces/User';

@Component({
  selector: 'shared-user-menu',
  templateUrl: './user-menu.component.html',
  styleUrls: ['./user-menu.component.css']
})
export class UserMenuComponent implements OnInit, OnDestroy {

  @Output() action = new EventEmitter();
  @Input() secundary: boolean = false;
  menuItems: userMenuItem[] = [];
  private subscription: Subscription;

  constructor ( private security: SecurityService) {
    this.subscription = this.security.event$.subscribe((user: User) => {
      this.menuItems = this.security.getMenu();
    });
  };

  ngOnInit() {
    this.menuItems = this.security.getMenu();
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

  emitAction() {
    this.action.emit();
  }
}
