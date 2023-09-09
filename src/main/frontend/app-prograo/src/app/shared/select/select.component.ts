import { Component, EventEmitter, Input, Output } from '@angular/core';
import { SelectConfiguration } from './SelectConfiguration';

@Component({
  selector: 'shared-select',
  templateUrl: './select.component.html',
  styleUrls: ['./select.component.css']
})
export class SelectComponent {

  @Output() dataEmit = new EventEmitter();
  @Input() conf!: SelectConfiguration;

  constructor() {}

  emit() {
    if (this.conf.formControl.dirty) {
      this.dataEmit.emit();
      this.conf.formControl.markAsPristine();
    }
  }
}
