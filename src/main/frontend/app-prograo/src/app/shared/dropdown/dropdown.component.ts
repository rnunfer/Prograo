import { Component, Input, OnInit } from '@angular/core';
import { dropdownConfiguration } from './dropdownConfiguration';
import { fadeIn, fadeOut } from '../animation';

@Component({
  selector: 'shared-dropdown',
  templateUrl: './dropdown.component.html',
  styleUrls: ['./dropdown.component.css'],
  animations: [fadeIn, fadeOut]
})
export class DropdownComponent implements OnInit {

  @Input() conf!: dropdownConfiguration;
  show!: boolean;

  ngOnInit() {
    this.show = this.conf.initialState;
  }

  changeShowNotConfirmation() {
    if (this.show) {
      this.show = false;
    } else {
      this.show = true;
    }
  }

  changeShow() {
    this.show = !this.show;
  }

}
