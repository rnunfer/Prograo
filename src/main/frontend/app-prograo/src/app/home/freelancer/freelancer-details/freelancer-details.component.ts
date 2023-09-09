import { Component, OnInit } from '@angular/core';
import { switchMap } from 'rxjs';
import { FreelancerBox } from 'src/app/interfaces/FreelancerBox';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { FreelancerListService } from '../freelancer-list.service';
import { FreelancerDetails } from 'src/app/interfaces/FreelancerDetails';

@Component({
  selector: 'app-freelancer-details',
  templateUrl: './freelancer-details.component.html',
  styleUrls: ['./freelancer-details.component.css']
})
export class FreelancerDetailsComponent implements OnInit{

  freelancers:FreelancerBox[] = [];
  freelancer!:FreelancerDetails;

  constructor( private activatedRoute : ActivatedRoute, private router: Router, private freelancerListService: FreelancerListService) {};
  
  ngOnInit() {
    this.init();   
  }

  async init() {
    this.activatedRoute.params
      .pipe(
        switchMap(async (param) => {
          this.freelancer = await this.freelancerListService.getFreelancerByFreelancerId(param['id']);
          if (this.freelancer.freelancerId == null)
            this.router.navigate(['/index']);
        })
      )
      .subscribe(() => {});
    this.freelancers = await this.freelancerListService.getAllFreelancerBoxs();
  }
}
