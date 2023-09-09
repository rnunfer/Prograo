import { Component, EventEmitter, Input, Output } from '@angular/core';
import { ButtonConfiguration } from './buttonConfiguration';

@Component({
  selector: 'shared-button',
  templateUrl: './button.component.html',
  styleUrls: ['./button.component.css']
})
export class ButtonComponent {

  @Output() clickEvent = new EventEmitter();
  @Input() conf: ButtonConfiguration = {
    value: 'Botón',
    type: 'primary',
    fontSize: 15,
    full: false
  }

  applyClass(): string {
    switch (this.conf.type) {
      case 'primary':
        return 'primary';
      case 'secondary':
        return 'secondary';
      case 'green':
        return 'green';
      default:
        return 'primary';
    }
  }

  clickAction() {
    this.clickEvent.emit();
  }
}
