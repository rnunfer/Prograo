import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FreelancersListComponent } from './freelancers-list/freelancers-list.component';
import { FreelancerDetailsComponent } from './freelancer-details/freelancer-details.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { FreelancerBoxComponent } from './components/freelancer-box/freelancer-box.component';
import { RouterModule } from '@angular/router';
import { FreelancerDetailInfoComponent } from './components/freelancer-detail-info/freelancer-detail-info.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';



@NgModule({
  declarations: [
    FreelancersListComponent,
    FreelancerDetailsComponent,
    FreelancerBoxComponent,
    FreelancerDetailInfoComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    RouterModule,
    FormsModule,
    ReactiveFormsModule
  ],
  exports: [
    FreelancerBoxComponent,
    FreelancerDetailsComponent
  ]
})
export class FreelancerListModule { }
