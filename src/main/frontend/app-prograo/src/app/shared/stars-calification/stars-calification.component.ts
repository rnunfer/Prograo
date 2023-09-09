import { AfterViewInit, Component, ElementRef, EventEmitter, Input, OnInit, Output, ViewChild } from '@angular/core';

@Component({
  selector: 'app-stars-calification',
  templateUrl: './stars-calification.component.html',
  styleUrls: ['./stars-calification.component.css']
})
export class StarsCalificationComponent {
  
  @Input() calification: number = 0;
  @Input() starSize: number = 15;
  @Input() numCalification: number = 0;
  @Input() text: boolean = false;
  @Output() emitNote = new EventEmitter<number>();

  setNote(i: number) {
    if(i == this.calification)
      i -= 2;
    this.emitNote.emit(i);
  }


}
