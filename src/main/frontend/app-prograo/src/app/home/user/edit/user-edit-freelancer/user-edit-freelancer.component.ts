import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Freelancer } from 'src/app/interfaces/Freelancer';
import { SecurityService } from 'src/app/security/security.service';
import { IInputConfiguration } from 'src/app/shared/input/IInputConfiguration';
import { UserEditService } from '../user-edit.service';

@Component({
  selector: 'app-user-edit-freelancer',
  templateUrl: './user-edit-freelancer.component.html',
  styleUrls: ['./user-edit-freelancer.component.css']
})
export class UserEditFreelancerComponent implements OnInit {

  freelancer!: Freelancer;

  formFreelancer: FormGroup = this.fb.group({
    description: new FormControl('', [ Validators.required, Validators.maxLength(1000) ]),
    rate: new FormControl('', [ Validators.required, Validators.min(0), Validators.max(9999) ]),
    twitter: new FormControl(''),
    facebook: new FormControl(''),
    linkedin: new FormControl(''),
    email: new FormControl('', [ Validators.email ])
  });

  confDescription: IInputConfiguration = {
    placeholder: 'Descripción',
    fontSize: 15,
    type: 'textarea',
    rowsNumberTextarea: 4,
    activateFormControl: true,
    formControl: this.formFreelancer.controls['description']
  };

  confRate: IInputConfiguration = {
    placeholder: 'Precio',
    fontSize: 15,
    type: 'number',
    rowsNumberTextarea: 0,
    activateFormControl: true,
    formControl: this.formFreelancer.controls['rate']
  };

  confTwitter: IInputConfiguration = {
    placeholder: 'Twitter',
    fontSize: 15,
    type: 'text',
    rowsNumberTextarea: 0,
    activateFormControl: true,
    formControl: this.formFreelancer.controls['twitter']
  };

  confFacebook: IInputConfiguration = {
    placeholder: 'Facebook',
    fontSize: 15,
    type: 'text',
    rowsNumberTextarea: 0,
    activateFormControl: true,
    formControl: this.formFreelancer.controls['facebook']
  };

  confLinkedin: IInputConfiguration = {
    placeholder: 'Linkedin',
    fontSize: 15,
    type: 'text',
    rowsNumberTextarea: 0,
    activateFormControl: true,
    formControl: this.formFreelancer.controls['linkedin']
  };

  confEmail: IInputConfiguration = {
    placeholder: 'Email',
    fontSize: 15,
    type: 'text',
    rowsNumberTextarea: 0,
    activateFormControl: true,
    formControl: this.formFreelancer.controls['email']
  };

  constructor (
    private userEditService: UserEditService,
    private fb: FormBuilder,
    private security: SecurityService,
  ) {
  }

  ngOnInit() {
    this.Init();
  }

  async Init() {
    this.freelancer = await this.userEditService.getFreelancerByUserId(this.security.getUserId());
    this.formFreelancer.controls['description'].setValue(this.freelancer.description);
    this.formFreelancer.controls['rate'].setValue(this.freelancer.rate);
    this.formFreelancer.controls['twitter'].setValue(this.freelancer.twitter);
    this.formFreelancer.controls['facebook'].setValue(this.freelancer.facebook);
    this.formFreelancer.controls['linkedin'].setValue(this.freelancer.linkedin);
    this.formFreelancer.controls['email'].setValue(this.freelancer.email);
  }

  async changeField(fieldName: string) {
    if (!this.formFreelancer.controls[fieldName].errors) {
      let freelancerAux: Freelancer = { ...this.freelancer };
      freelancerAux[fieldName] = this.formFreelancer.value[fieldName];
      let result = await this.userEditService.editFreelancer(freelancerAux);
      if (result) {
        this.freelancer = freelancerAux;
      }
    }
  }

}
