import { Component, EventEmitter, Input, OnDestroy, OnInit, Output } from '@angular/core';
import { ModalService } from './modal.service';
import { IModalConfiguration } from './IModalConfiguration';
import { Subscription } from 'rxjs';
import { trigger, transition, style, animate } from '@angular/animations';
import { fadeIn, fadeOut } from '../animation';

@Component({
  selector: 'shared-modal',
  templateUrl: './modal.component.html',
  styleUrls: ['./modal.component.css'],
  animations: [fadeIn, fadeOut]
})
export class ModalComponent implements OnInit, OnDestroy {

  showModal: boolean = false;
  @Input() modalConfiguration!: IModalConfiguration;
  @Output() modalClosed = new EventEmitter();
  private subscription: Subscription;

  constructor ( private modalService: ModalService ) {
    this.subscription = this.modalService.event$.subscribe((modal) => {
      if(modal == this.modalConfiguration.modal) {
        this.open();
      }
    });
    this.subscription = this.modalService.close$.subscribe((modal) => {
      if(modal == this.modalConfiguration.modal) {
        this.hide();
      }
    });
  }

  ngOnInit() {}

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

  applyConfiguration(): any {
    let style = {
      "min-width": this.modalConfiguration.minWidth + 'px',
      "min-height": this.modalConfiguration.minHeight + 'px',
      "max-width": this.modalConfiguration.maxWidth + 'px',
      "max-height": this.modalConfiguration.maxHeight + 'px',
      "width": this.modalConfiguration.width + '%'

    }
    return style;
  }

  open() {
    this.showModal= true;
    document.body.classList.add('no-scroll');
  }

  hide() {
    this.showModal = false;
    this.modalClosed.emit();
    document.body.classList.remove('no-scroll');
  }
}
