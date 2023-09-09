import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserProjectListComponent } from './user-project-list/user-project-list.component';
import { UserProjectDetailsComponent } from './user-project-details/user-project-details.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';



@NgModule({
  declarations: [
    UserProjectListComponent,
    UserProjectDetailsComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    RouterModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class UserProjectModule { }
