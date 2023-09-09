import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserEditComponent } from './user-edit/user-edit.component';
import { UserEditFreelancerComponent } from './user-edit-freelancer/user-edit-freelancer.component';
import { UserEditSkillsComponent } from './user-edit-skills/user-edit-skills.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { RouterModule } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';



@NgModule({
  declarations: [
    UserEditComponent,
    UserEditFreelancerComponent,
    UserEditSkillsComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    RouterModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class UserEditModule { }
