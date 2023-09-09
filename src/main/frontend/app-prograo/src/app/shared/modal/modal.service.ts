import { EventEmitter, Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable()
export class ModalService {
  
  private eventSubject = new Subject<string>();
  private closeModal = new Subject<string>();
  event$ = this.eventSubject.asObservable();
  close$ = this.closeModal.asObservable();

  constructor() {}

  openModal(modal: string) {
    this.eventSubject.next(modal);
  }

  closeModalAction(modal: string) {
    this.closeModal.next(modal);
  }
    
}
