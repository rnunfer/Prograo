import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProposalDetailsComponent } from './proposal-details/proposal-details.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { UserProposalListComponent } from './user-proposal-list/user-proposal-list.component';
import { UserProposalEditComponent } from './user-proposal-edit/user-proposal-edit.component';



@NgModule({
  declarations: [
    UserProposalListComponent,
    ProposalDetailsComponent,
    UserProposalEditComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule
  ]
})
export class UserProposalModule { }
