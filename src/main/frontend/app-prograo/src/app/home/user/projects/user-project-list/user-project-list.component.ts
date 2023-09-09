import { Component } from '@angular/core';
import { SecurityService } from 'src/app/security/security.service';
import { dropdownConfiguration } from 'src/app/shared/dropdown/dropdownConfiguration';
import { Subscription } from 'rxjs';
import { User } from 'src/app/interfaces/User';
import { Project } from 'src/app/interfaces/Project';
import { UserProjectService } from '../user-project.service';

@Component({
  selector: 'app-user-project-list',
  templateUrl: './user-project-list.component.html',
  styleUrls: ['./user-project-list.component.css']
})
export class UserProjectListComponent {

  allProjectList: Project[] = [];
  private subscription: Subscription;
  projectList: Project[] = [];
  preProjectList: Project[] = [];
  finishedProjectList: Project[] = [];
  showNotConfirmation: boolean = true;
  showConfirmated: boolean = true;
  userType: string = "";

  conf1: dropdownConfiguration = {
    title: "Projectos en curso",
    initialState: true
  }

  conf2: dropdownConfiguration = {
    title: "Pre-proyectos",
    initialState: true
  }

  conf3: dropdownConfiguration = {
    title: "Proyectos finalizados",
    initialState: true
  }

  constructor ( private security: SecurityService, private userProjectService: UserProjectService ) {
    this.subscription = this.security.event$.subscribe((user: User) => {
      this.userType = this.security.getUserTypeUser();
      this.getAllProjects();
    });
  }

  ngOnInit() {
    this.getAllProjects();
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

  async getAllProjects() {
    this.allProjectList = await this.userProjectService.getAllProjects();
    this.allProjectList = this.allProjectList.map(project => ({
      ...project,
      projectSendDate: project.projectSendDate ? new Date(project.projectSendDate) : null,
      projectStartDate: project.projectStartDate ? new Date(project.projectStartDate) : null,
      projectFinishDate: project.projectFinishDate ? new Date(project.projectFinishDate) : null,
    }));

    this.projectList = this.allProjectList.filter(project => project.projectStatus === 'inprogress');
    this.preProjectList = this.allProjectList.filter(project => project.projectStatus === 'preproject');
    this.finishedProjectList = this.allProjectList.filter(project => project.projectStatus === 'finished');

    this.projectList.sort((a, b) => (b.projectSendDate ? b.projectSendDate.getTime() : 0) - (a.projectSendDate ? a.projectSendDate.getTime() : 0));
    this.preProjectList.sort((a, b) => (b.projectStartDate ? b.projectStartDate.getTime() : 0) - (a.projectStartDate ? a.projectStartDate.getTime() : 0));
    this.finishedProjectList.sort((a, b) => (b.projectFinishDate ? b.projectFinishDate.getTime() : 0) - (a.projectFinishDate ? a.projectFinishDate.getTime() : 0));

    this.userType = this.security.getUserTypeUser();
  }

  changeShowNotConfirmation() {
    if (this.showNotConfirmation) {
      this.showNotConfirmation = false;
    } else {
      this.showNotConfirmation = true;
    }
  }

  changeShowConfirmated() {
    if (this.showConfirmated) {
      this.showConfirmated = false;
    } else {
      this.showConfirmated = true;
    }
  }
}
