import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FreelancerDetailsComponent } from './home/freelancer/freelancer-details/freelancer-details.component';
import { FreelancersListComponent } from './home/freelancer/freelancers-list/freelancers-list.component';
import { IndexComponent } from './home/index/index.component';
import { UserProposalListComponent } from './home/user/proposals/user-proposal-list/user-proposal-list.component';
import { UserEditComponent } from './home/user/edit/user-edit/user-edit.component';
import { UserFrameComponent } from './home/user/user-frame/user-frame.component';
import { UserProjectListComponent } from './home/user/projects/user-project-list/user-project-list.component';
import { UserProjectDetailsComponent } from './home/user/projects/user-project-details/user-project-details.component';
import { UserTypeGuard } from './security/userTypeGuard';

const routes: Routes = [
  { path: "index", component: IndexComponent, pathMatch: "full" },
  { path: "freelancer-list", children: [
    { path: "all", component: FreelancersListComponent },
    { path: ":id", component: FreelancerDetailsComponent },
  ]},
  { path: "user", component: UserFrameComponent, canActivate: [UserTypeGuard], children: [
    { path: "configuration", component: UserEditComponent },
    { path: "proposals", component: UserProposalListComponent },
    { path: "projects", component: UserProjectListComponent },
    { path: "projects/:id", component: UserProjectDetailsComponent }
  ]},
  { path: "**", redirectTo: "index" }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { scrollPositionRestoration: 'enabled' })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
