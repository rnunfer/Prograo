import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { concatMap, tap } from 'rxjs';
import { FreelancerSkill } from 'src/app/interfaces/FreelancerSkill';
import { Skill } from 'src/app/interfaces/Skill';
import { SecurityService } from 'src/app/security/security.service';
import { UserEditService } from '../user-edit.service';

@Component({
  selector: 'app-user-edit-skills',
  templateUrl: './user-edit-skills.component.html',
  styleUrls: ['./user-edit-skills.component.css']
})
export class UserEditSkillsComponent implements OnInit {

  skillsList!: Skill[];
  freelancerSkillsList!: FreelancerSkill[];
  numberOutstandingSkills: number = 0;
  filteredData: Skill[] = [];
  filterSearch: string = "";

  formSkills: FormGroup = this.fb.group({
    filterSearch: new FormControl(''),
  });

  constructor (
    private userEditService: UserEditService,
    private fb: FormBuilder,
    private security: SecurityService
  ) {}

  ngOnInit(): void {
    this.userEditService.getAllFreelancerSkill(this.security.getUserId()).pipe(
      tap((data: FreelancerSkill[]) => {
        this.freelancerSkillsList = data;
        this.freelancerSkillsList.forEach((skill) => {
          if (skill.outstanding) {
            this.numberOutstandingSkills++;
          }
        });
        console.log(this.freelancerSkillsList);
      }),
      concatMap(() => this.userEditService.getAllSkills()),
      tap((data: Skill[]) => {
        this.skillsList = data;
        this.skillsList = this.skillsList.filter(skill =>
          !this.freelancerSkillsList.some(freelancerSkill => freelancerSkill.name === skill.name)
        );
      }),
      tap(() => {
        this.filteredData = this.skillsList;
        this.sortData();
      })
    ).subscribe(() => {});
  }

  searchDataSkills(searchValue: string) {
    this.filteredData = this.skillsList.filter((skill: Skill) => {
      return skill.name.toLowerCase().includes(searchValue.toLowerCase());
    });
  }

  addToFreelancerSkills(i: number) {
    this.userEditService.addSkillToFreelancer(this.security.getUserId(), this.filteredData[i].id).subscribe({
      next: (result: boolean) => {
        if (result) {
          let skillToAdd: FreelancerSkill = {
            id: this.filteredData[i].id,
            name: this.filteredData[i].name,
            outstanding: false
          }
          this.freelancerSkillsList.push(skillToAdd);
          this.skillsList = this.skillsList.filter(skill => skill.id !== this.filteredData[i].id);
          this.filteredData = this.filteredData.filter(skill => skill.id !== this.filteredData[i].id);
        }
      }
    })   
  }

  changeOutstanding(i: number) {
    this.userEditService.changeOutstandingSkillOfFreelancer(this.security.getUserId(), this.freelancerSkillsList[i].id).subscribe({
      next: (result: boolean) => {
        if(this.freelancerSkillsList[i].outstanding) {
          this.freelancerSkillsList[i].outstanding = false;
          this.numberOutstandingSkills--;
        } else {
          this.freelancerSkillsList[i].outstanding = true;
          this.numberOutstandingSkills++;
        }
      }
    })
  }

  removeFromFreelancerSkills(i: number) {
    this.userEditService.removeSkillOfFreelancer(this.security.getUserId(), this.freelancerSkillsList[i].id).subscribe({
      next: (result: boolean) => {
        if (result) {
          let skillToRemove: Skill = {
            id: this.freelancerSkillsList[i].id,
            name: this.freelancerSkillsList[i].name,
          }
          this.filteredData.push(skillToRemove);
          this.skillsList.push(skillToRemove);
          if (this.freelancerSkillsList[i].outstanding) {
            this.numberOutstandingSkills--;
          }
          this.freelancerSkillsList.splice(i, 1);
          this.sortData();
        }
      }
    })  
  }

  sortData() {
    this.filteredData.sort((a: Skill, b: Skill) => a.name.localeCompare(b.name));
    this.skillsList.sort((a: Skill, b: Skill) => a.name.localeCompare(b.name));
  }
}
