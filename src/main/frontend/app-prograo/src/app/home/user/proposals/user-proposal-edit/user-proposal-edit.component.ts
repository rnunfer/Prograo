import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Proposal } from 'src/app/interfaces/Proposal';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { IInputConfiguration } from 'src/app/shared/input/IInputConfiguration';
import { UserProposalService } from '../user-proposal.service';
import { SelectConfiguration } from 'src/app/shared/select/SelectConfiguration';

@Component({
  selector: 'app-user-proposal-edit',
  templateUrl: './user-proposal-edit.component.html',
  styleUrls: ['./user-proposal-edit.component.css']
})
export class UserProposalEditComponent implements OnInit {

  @Output() changeEmit = new EventEmitter();
  @Input() proposal!: Proposal;

  formProposal: FormGroup = this.fb.group({
    proposalTitle: new FormControl('', [ Validators.required ]),
    proposalDescription: new FormControl('', [ Validators.required ]),
    proposalStimatedTime: new FormControl('', [ Validators.required ]),
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

  confStimatedTime: IInputConfiguration = {
    placeholder: '',
    fontSize: 15,
    type: 'text',
    rowsNumberTextarea: 0,
    activateFormControl: true,
    formControl: this.formProposal.controls['proposalStimatedTime']
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
        label: "Sin determinar"
      }
    ]
  }

  constructor(private userProposalService: UserProposalService, private fb: FormBuilder) {}

  ngOnInit() {
    this.formProposal.controls['proposalTitle'].setValue(this.proposal.proposalTitle);
    this.formProposal.controls['proposalDescription'].setValue(this.proposal.proposalDescription);
    this.formProposal.controls['proposalStimatedTime'].setValue(this.proposal.proposalEstimatedTime);
    this.formProposal.controls['proposalWorkStyle'].setValue(this.proposal.proposalWorkStyle);
  }

  async changeField(fieldName: string) {
    if (!this.formProposal.controls[fieldName].errors) {
      let proposalAux: Proposal = { ...this.proposal };
      proposalAux[fieldName] = this.formProposal.value[fieldName];
      let result = await this.userProposalService.editProposal(proposalAux)
      if (result) {
        this.proposal = proposalAux;
        this.changeEmit.emit();
      }
    }
  }
}
