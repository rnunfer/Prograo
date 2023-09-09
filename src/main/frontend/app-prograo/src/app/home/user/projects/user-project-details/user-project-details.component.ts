import { Component, ElementRef, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { Project } from 'src/app/interfaces/Project';
import { IInputConfiguration } from 'src/app/shared/input/IInputConfiguration';
import { UserProjectService } from '../user-project.service';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { ButtonConfiguration } from 'src/app/shared/button/buttonConfiguration';
import { SelectConfiguration } from 'src/app/shared/select/SelectConfiguration';
import { Subscription, switchMap } from 'rxjs';
import { SecurityService } from 'src/app/security/security.service';
import { User } from 'src/app/interfaces/User';
import { Chat } from 'src/app/interfaces/Chat';
import { Skill } from 'src/app/interfaces/Skill';
import { FreelancerSkill } from 'src/app/interfaces/FreelancerSkill';

@Component({
  selector: 'app-user-project-details',
  templateUrl: './user-project-details.component.html',
  styleUrls: ['./user-project-details.component.css']
})
export class UserProjectDetailsComponent implements OnInit, OnDestroy{

  @ViewChild('messageInput') messageInput!: ElementRef;
  private subscription: Subscription;
  userType: string = "";
  userId: number = 0;
  project !: Project;
  messageList : Chat[] = [];
  timer: any;
  skillsList: FreelancerSkill[] = [];
  filteredData: FreelancerSkill[] = [];
  filterSearch: string = "";

  formSkills: FormGroup = this.fb.group({
    filterSearch: new FormControl(''),
  });

  formProject: FormGroup = this.fb.group({
    projectTitle: new FormControl('', [ Validators.required ]),
    projectDescription: new FormControl('', [ Validators.required ]),
    projectWorkStyle: new FormControl('', [ Validators.required ]),
    projectContractPrice: new FormControl('', [ Validators.required, Validators.min(0) ]),
    projectDeadline: new FormControl('')
  });

  formCalification: FormGroup = this.fb.group({
    content: new FormControl('')
  });

  confWorkStyleSelect: SelectConfiguration = {
    fontSize: 15,
    activateFormControl: true,
    formControl: this.formProject.controls['projectWorkStyle'],
    options: [
      {
        value: "remoteWork",
        label: "Teletrabajo"
      },
      {
        value: "inPersonWork",
        label: "Presencial"
      },
      {
        value: "hybrid",
        label: "Híbrido"
      },
      {
        value: "undefined",
        label: "Sin definir"
      }
    ]
  }

  confSign: ButtonConfiguration = {
    value: 'Firmar',
    type: 'green',
    fontSize: 16,
    full: false
  }

  confCalification: ButtonConfiguration = {
    value: 'Enviar',
    type: 'primary',
    fontSize: 15,
    full: false
  }

  confCalificationDescription: IInputConfiguration = {
    placeholder: 'Escriba su opinión del proyecto y del freelancer',
    fontSize: 15,
    type: 'textarea',
    rowsNumberTextarea: 6,
    activateFormControl: true,
    formControl: this.formCalification.controls['content']
  };

  confTitle: IInputConfiguration = {
    placeholder: 'Título',
    fontSize: 15,
    type: 'text',
    rowsNumberTextarea: 0,
    activateFormControl: true,
    formControl: this.formProject.controls['projectTitle']
  };

  confDescription: IInputConfiguration = {
    placeholder: 'Descripción',
    fontSize: 15,
    type: 'textarea',
    rowsNumberTextarea: 6,
    activateFormControl: true,
    formControl: this.formProject.controls['projectDescription']
  };

  confPrice: IInputConfiguration = {
    placeholder: 'Precio de contratación',
    fontSize: 15,
    type: 'number',
    rowsNumberTextarea: 0,
    activateFormControl: true,
    formControl: this.formProject.controls['projectContractPrice']
  };

  constructor (
    private userProjectService: UserProjectService,
    private securityService:SecurityService,
    private fb: FormBuilder,
    private activatedRoute : ActivatedRoute,
  ) {
    this.timer = setInterval(() => {
      this.loadChat();
      if (this.formProject) {
        if (!this.formProject.dirty && !this.formCalification.dirty) {
          this.loadProject();
        }
      }
    }, 300);
    this.subscription = this.securityService.event$.subscribe((user: User) => {
      this.userType = this.securityService.getUserTypeUser();
      this.userId = this.securityService.getUserId();
      this.loadProject();
    });
  }
 
  ngOnInit() {
    this.loadProject();
    this.userType = this.securityService.getUserTypeUser();
    this.userId = this.securityService.getUserId();
  }

  ngOnDestroy(): void {
    clearInterval(this.timer);
    this.subscription.unsubscribe();
  }

  loadProject() {
    this.activatedRoute.params
      .pipe(
        switchMap(async (param) => {
          this.project = await this.userProjectService.getProjectById(param['id']);
          if (this.project.projectId != null) {
            this.formProject.controls['projectTitle'].setValue(this.project.projectTitle);
            this.formProject.controls['projectDescription'].setValue(this.project.projectDescription);
            this.formProject.controls['projectWorkStyle'].setValue(this.project.projectWorkStyle);
            this.formProject.controls['projectDeadline'].setValue(this.project.projectDeadline);
            this.formProject.controls['projectContractPrice'].setValue(this.project.projectContractPrice);
            this.formCalification.controls['content'].setValue(this.project.calificationDescription);
          }
          if (this.project.userFreelancerId !== null) {
            this.skillsList = await this.userProjectService.getAllFreelancerSkill(this.project.userFreelancerId);
            if (this.project.skillList == null)
              this.project.skillList = [];
            this.skillsList = await this.skillsList.filter(skill =>
              !this.project.skillList.some(projectSkill => projectSkill.name === skill.name)
            );
            this.filteredData = this.skillsList;
            this.sortData();
          }
        })
      )
      .subscribe(() => {});
  }

  async loadChat() {
    if (this.project) {
      this.messageList = await this.userProjectService.getAllChatsFromProject(this.project.projectId);
      this.messageList.sort((a, b) => {
        const dateA = new Date(a.chatDate).getTime();
        const dateB = new Date(b.chatDate).getTime();
        return dateB - dateA;
      });
    }    
  }

  async changeField(fieldName: string) {
    if (!this.formProject.controls[fieldName].errors) {
      let projectAux: Project = { ...this.project };
      projectAux[fieldName] = this.formProject.value[fieldName];
      let result = await this.userProjectService.editProject(projectAux);
      if (result)
        this.project = projectAux;
    }
  }

  async signSeeker() {
    let result: boolean = await this.userProjectService.signProjectBySeeker(this.project.projectId);
    if (result)
      this.loadProject();
  }

  async signFreelancer() {
    let result: boolean = await this.userProjectService.signProjectByFreelancer(this.project.projectId);
    if (result)
      this.loadProject();
  }

  changeNote(event: any) {
    if (this.userType == 'seeker')
      this.project.calificationNote = event;
      this.sendCalification();
  }

  searchDataSkills(searchValue: string) {
    this.filteredData = this.skillsList.filter((skill: FreelancerSkill) => {
      return skill.name.toLowerCase().includes(searchValue.toLowerCase());
    });
  }

  sortData() {
    this.filteredData.sort((a: FreelancerSkill, b: FreelancerSkill) => a.name.localeCompare(b.name));
    this.skillsList.sort((a: FreelancerSkill, b: FreelancerSkill) => a.name.localeCompare(b.name));
  }

  async addToProjectSkills(i: number) {
    let result: boolean = await this.userProjectService.addSkillToProject(this.filteredData[i].id, this.project.projectId);
    if (result) {
      let skillToAdd: Skill = {
        id: this.filteredData[i].id,
        name: this.filteredData[i].name,
      }
      this.project.skillList.push(skillToAdd);    
      this.skillsList = this.skillsList.filter(skill => skill.id !== this.filteredData[i].id);
      this.filteredData = this.filteredData.filter(skill => skill.id !== this.filteredData[i].id);
    }
  }

  async removeFromProjectSkills(i: number) {
    let result: boolean = await this.userProjectService.removeSkillFromProject(this.project.skillList[i].id, this.project.projectId);
    if (result) {
      let skillToRemove: FreelancerSkill = {
        id: this.project.skillList[i].id,
        name: this.project.skillList[i].name,
        outstanding: false
      }
      this.filteredData.push(skillToRemove);
      this.skillsList.push(skillToRemove);
      this.project.skillList.splice(i,1);
      this.sortData();
    }
  }

  sendCalification() {
    if (this.userType == 'seeker')
      this.userProjectService.sendCalification(this.project.projectId, this.formCalification.controls['content'].value, this.project.calificationNote);
  }

  sendMessage(message: string) {
    const projectId: number = this.project.projectId;
    this.userProjectService.sendMessageToProject(projectId, message)
      .then(() => {
        this.messageInput.nativeElement.value = '';
      })
      .catch((error) => {
        console.error(error);
      });
  }
}
