import { Component, EventEmitter, Input, Output } from '@angular/core';
import { IInputConfiguration } from './IInputConfiguration';
import { fadeIn, fadeOut } from '../animation';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'shared-input',
  templateUrl: './input.component.html',
  styleUrls: ['./input.component.css'],
  animations: [fadeIn, fadeOut],
  providers: [DatePipe]
})
export class InputComponent {

  @Output() dataEmit = new EventEmitter();
  @Input() conf!: IInputConfiguration;

  constructor (public datePipe: DatePipe) {}

  emit() {
    if (this.conf.formControl.dirty) {
      /* const formattedDate = this.datePipe.transform(this.conf.formControl.value, 'yyyy-MM-dd');
      this.conf.formControl.setValue(formattedDate); */
      this.dataEmit.emit();
      this.conf.formControl.markAsPristine();
    }
  }

  formatDate(value: string) {
    const formattedValue = this.datePipe.transform(value, 'dd-MM-yyyy');
    this.conf.formControl.setValue(formattedValue);
  }
}
