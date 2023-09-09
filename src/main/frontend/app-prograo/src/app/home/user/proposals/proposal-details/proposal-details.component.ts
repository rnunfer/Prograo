import { Component, Input } from '@angular/core';
import { Proposal } from 'src/app/interfaces/Proposal';

@Component({
  selector: 'app-proposal-details',
  templateUrl: './proposal-details.component.html',
  styleUrls: ['./proposal-details.component.css']
})
export class ProposalDetailsComponent {

  @Input() proposal!: Proposal;
}
