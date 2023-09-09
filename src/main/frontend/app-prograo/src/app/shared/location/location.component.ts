import { Component, Input } from '@angular/core';

@Component({
  selector: 'shared-location',
  templateUrl: './location.component.html',
  styleUrls: ['./location.component.css']
})
export class LocationComponent {

  @Input() city: string = "";
  @Input() country: string = "";
  @Input() fontSizeIcon: number = 1;
  @Input() fontSizeP: number = 1;
}
