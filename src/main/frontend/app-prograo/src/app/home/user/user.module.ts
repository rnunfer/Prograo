import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from 'src/app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { UserEditModule } from './edit/user-edit.module';
import { UserFrameComponent } from './user-frame/user-frame.component';
import { RouterModule } from '@angular/router';
import { UserProjectModule } from './projects/user-project.module';
import { UserProposalModule } from './proposals/user-proposal.module';

@NgModule({
  declarations: [
    UserFrameComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    FormsModule,
    RouterModule,
    ReactiveFormsModule,
    UserEditModule,
    UserProjectModule,
    UserProposalModule
  ],
  exports: [],
  providers: []
})
export class UserModule { }
