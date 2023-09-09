import { Component, Input, OnInit } from '@angular/core';
import { FreelancerBox } from 'src/app/interfaces/FreelancerBox';

@Component({
  selector: 'app-freelancer-box',
  templateUrl: './freelancer-box.component.html',
  styleUrls: ['./freelancer-box.component.css']
})
export class FreelancerBoxComponent implements OnInit {

  @Input() freelancer!: FreelancerBox;
  skillList: String[] = [];

  constructor() {}

  ngOnInit(): void {
    if (this.freelancer.skills !== null) {
      this.skillList = this.freelancer.skills.split(",");
    }

    if(this.freelancer.totalCalification === null) {
      this.freelancer.totalCalification = 0;
    }
  }

}
