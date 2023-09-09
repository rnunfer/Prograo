import { Component, OnInit } from '@angular/core';
import { FreelancerBox } from 'src/app/interfaces/FreelancerBox';
import { FreelancerListService } from '../freelancer-list.service';

@Component({
  selector: 'app-freelancers-list',
  templateUrl: './freelancers-list.component.html',
  styleUrls: ['./freelancers-list.component.css']
})
export class FreelancersListComponent implements OnInit{

  freelancers:FreelancerBox[] = [];

  constructor( private freelancerListService: FreelancerListService) {};
  
  async ngOnInit() {
    this.init();
  }

  async init() {
    this.freelancers = await this.freelancerListService.getAllFreelancerBoxs();
  }

}
