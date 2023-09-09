import { Component, OnDestroy } from '@angular/core';
import { IModalConfiguration } from 'src/app/shared/modal/IModalConfiguration';
import { ModalService } from 'src/app/shared/modal/modal.service';
import { trigger, transition, style, animate } from '@angular/animations';
import { SecurityService } from 'src/app/security/security.service';
import { User } from 'src/app/interfaces/User';
import { Subscription } from 'rxjs';
import { ButtonConfiguration } from '../button/buttonConfiguration';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnDestroy {

  user: User = {
    id: 0,
    name: '',
    email: '',
    title: '',
    profilePhoto: '',
    status: '',
    verified: false,
    city: '',
    country: '',
    userType: 'guest'
  }

  private subscription: Subscription;
  menuStatus: string = "";

  modalConfiguration: IModalConfiguration = {
    modal: 'loginRegister',
    name: 'Identificación',
    minWidth: 300,
    minHeight: 0,
    maxWidth: 600,
    maxHeight: 800,
    width: 30
  }

  buttonConfiguration: ButtonConfiguration = {
    value: 'Identificarse',
    type: 'primary',
    fontSize: 15,
    full: false
  }

  constructor( private modalService: ModalService, private security: SecurityService) {
    this.subscription = this.security.event$.subscribe((user: User) => {
      this.user = user;
      this.menuStatus = "";
    });
  };

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

  openLoginRegister() {
    this.modalService.openModal("loginRegister");
  }

  openCloseMenu() {
    if (this.menuStatus == "userMenu") {
      this.menuStatus = "";
    } else {
      this.menuStatus = "userMenu";
    }
  }

  closeSession() {
    this.security.closeSession();
  }

}
