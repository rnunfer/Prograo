import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from '../shared/shared.module';
import { UserModule } from './user/user.module';
import { FreelancerListModule } from './freelancer/freelancer-list.module';
import { IndexModule } from './index/index.module';



@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    SharedModule,
    UserModule,
    FreelancerListModule,
    IndexModule
  ]
})
export class HomeModule { }
