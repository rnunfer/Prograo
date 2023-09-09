import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Subscription } from 'rxjs';
import { FreelancerDetails } from 'src/app/interfaces/FreelancerDetails';
import { FreelancerDetailsSkills } from 'src/app/interfaces/FreelancerDetailsSkills';
import { SecurityService } from 'src/app/security/security.service';
import { ButtonConfiguration } from 'src/app/shared/button/buttonConfiguration';
import { IInputConfiguration } from 'src/app/shared/input/IInputConfiguration';
import { SelectConfiguration } from 'src/app/shared/select/SelectConfiguration';
import { FreelancerListService } from '../../freelancer-list.service';
import { Route, Router } from '@angular/router';

@Component({
  selector: 'app-freelancer-detail-info',
  templateUrl: './freelancer-detail-info.component.html',
  styleUrls: ['./freelancer-detail-info.component.css']
})
export class FreelancerDetailInfoComponent implements OnInit {

  @Input() freelancer!: FreelancerDetails;
  @Input() skills!: FreelancerDetailsSkills[];
  userType: string = 'guest';
  status: string = ''; 
  private subscription: Subscription;

  formProposal: FormGroup = this.fb.group({
    proposalTitle: new FormControl('', [ Validators.required ]),
    proposalDescription: new FormControl('', [ Validators.required ]),
    proposalEstimatedTime: new FormControl('', [ Validators.required ]),
    proposalWorkStyle: new FormControl('', [ Validators.required ])
  });

  confTitle: IInputConfiguration = {
    placeholder: '',
    fontSize: 15,
    type: 'text',
    rowsNumberTextarea: 0,
    activateFormControl: true,
    formControl: this.formProposal.controls['proposalTitle']
  };

  confDescription: IInputConfiguration = {
    placeholder: '',
    fontSize: 15,
    type: 'textarea',
    rowsNumberTextarea: 8,
    activateFormControl: true,
    formControl: this.formProposal.controls['proposalDescription']
  };

  confEstimatedTime: IInputConfiguration = {
    placeholder: '',
    fontSize: 15,
    type: 'text',
    rowsNumberTextarea: 0,
    activateFormControl: true,
    formControl: this.formProposal.controls['proposalEstimatedTime']
  };

  confWorkStyleSelect: SelectConfiguration = {
    fontSize: 15,
    activateFormControl: true,
    formControl: this.formProposal.controls['proposalWorkStyle'],
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
        value: "hybridWork",
        label: "Híbrido"
      },
      {
        value: "undefined",
        label: "Sin definir"
      }
    ]
  }

  confProposalSubmit: ButtonConfiguration = {
    value: 'Enviar',
    type: 'primary',
    fontSize: 15,
    full: false
  }

  constructor (private fb: FormBuilder, private securityService: SecurityService, private freelancerListService: FreelancerListService, private router: Router) {
    this.subscription = this.securityService.event$.subscribe(() => {
      this.userType = this.securityService.getUserTypeUser();
      this.status = this.securityService.getStatus();
    });
  }

  ngOnInit(): void {
    this.userType = this.securityService.getUserTypeUser();
    this.status = this.securityService.getStatus();
  }

  async sendProposal() {
    console.log(this.formProposal.controls['proposalTitle'].value);
    if (this.formProposal.valid) {
      let data = {
        title: this.formProposal.value['proposalTitle'],
        description: this.formProposal.value['proposalDescription'],
        workStyle: this.formProposal.value['proposalWorkStyle'],
        estimatedTime: this.formProposal.value['proposalEstimatedTime'],
        freelancerId: this.freelancer.freelancerId
      }
      console.log(data);
      let result = await this.freelancerListService.sendProposal(data);
      if(result) {
        this.router.navigate(['/user/proposals']);
      }
    } else {
      this.formProposal.markAllAsTouched();
    }
  }

}
