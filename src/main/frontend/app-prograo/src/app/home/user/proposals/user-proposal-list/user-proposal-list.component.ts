import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Proposal } from 'src/app/interfaces/Proposal';
import { ModalService } from 'src/app/shared/modal/modal.service';
import { IModalConfiguration } from 'src/app/shared/modal/IModalConfiguration';
import { fadeIn, fadeOut, slideDown, slideUp } from 'src/app/shared/animation';
import { dropdownConfiguration } from 'src/app/shared/dropdown/dropdownConfiguration';
import { SecurityService } from 'src/app/security/security.service';
import { Subscription } from 'rxjs';
import { User } from 'src/app/interfaces/User';
import { UserProposalService } from '../user-proposal.service';

@Component({
  selector: 'app-user-proposal-list',
  templateUrl: './user-proposal-list.component.html',
  styleUrls: ['./user-proposal-list.component.css'],
  animations: [fadeIn, fadeOut]
})
export class UserProposalListComponent implements OnInit, OnDestroy {

  proposalList: Proposal[] = [];
  private subscription: Subscription;
  confirmedList: Proposal[] = [];
  noConfirmedList: Proposal[] = [];
  proposalToModal: Proposal = {
    proposalId: 0,
    proposalTitle: '',
    proposalDescription: '',
    proposalEstimatedTime: '',
    proposalWorkStyle: '',
    proposalStatus: '',
    proposalSendDate: null,
    proposalConfirmDate: null,
    userSeekerId: 0,
    seekerName: '',
    seekerProfilePhoto: '',
    seekerTitle: '',
    seekerCity: '',
    seekerCountry: '',
    userFreelancerId: 0,
    userFreelancerName: ''
  }
  showNotConfirmation: boolean = true;
  showConfirmated: boolean = true;
  userType: string = "";

  conf1: dropdownConfiguration = {
    title: "Propuestas sin confirmar",
    initialState: true
  }

  conf2: dropdownConfiguration = {
    title: "Propuestas confirmadas",
    initialState: true
  }

  modalConfiguration: IModalConfiguration = {
    modal: 'proposalDetails',
    name: 'Propuesta de proyecto',
    minWidth: 300,
    minHeight: 0,
    maxWidth: 600,
    maxHeight: 800,
    width: 50
  }

  modalEdit: IModalConfiguration = {
    modal: 'proposalEdit',
    name: 'Editar propuesta de proyecto',
    minWidth: 300,
    minHeight: 0,
    maxWidth: 600,
    maxHeight: 800,
    width: 50
  }

  constructor ( private modalService: ModalService, private security: SecurityService, private userProposalService: UserProposalService ) {
    this.subscription = this.security.event$.subscribe((user: User) => {
      this.userType = this.security.getUserTypeUser();
      this.getAllProposals();
    });
  }

  ngOnInit() {
    this.getAllProposals();
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

  async getAllProposals() {
    this.noConfirmedList = [];
    this.confirmedList = [];
    this.proposalList = await this.userProposalService.getAllProposals();
  
    this.proposalList = this.proposalList.map(proposal => ({
      ...proposal,
      proposalSendDate: proposal.proposalSendDate ? new Date(proposal.proposalSendDate) : null,
      proposalConfirmDate: proposal.proposalConfirmDate ? new Date(proposal.proposalConfirmDate) : null
    }));
  
    this.noConfirmedList = this.proposalList.filter(proposal => proposal.proposalStatus === 'waiting');
    this.confirmedList = this.proposalList.filter(proposal => proposal.proposalStatus === 'accepted' || proposal.proposalStatus === 'rejected');
  
    this.noConfirmedList.sort((a, b) => (b.proposalSendDate ? b.proposalSendDate.getTime() : 0) - (a.proposalSendDate ? a.proposalSendDate.getTime() : 0));
    this.confirmedList.sort((a, b) => (b.proposalSendDate ? b.proposalSendDate.getTime() : 0) - (a.proposalSendDate ? a.proposalSendDate.getTime() : 0));
  
    this.userType = this.security.getUserTypeUser();
    console.log(this.noConfirmedList);
    console.log(this.confirmedList);
  }
  
  

  async confirmProposal(i: number, type: string) {
    let proposal: Proposal = this.noConfirmedList[i];
    let result: boolean = await this.userProposalService.confirmProposal(proposal.proposalId, type)
    if (result) {
      this.getAllProposals();
    }   
  }

  async editProposal(i: number) {
    if (await this.userProposalService.getProposalByProposalId(this.noConfirmedList[i].proposalId)) {
      this.proposalToModal = this.noConfirmedList[i];
      this.modalService.openModal("proposalEdit");
    }
  }

  async deleteProposal(i: number) {
    let proposal: Proposal = this.noConfirmedList[i];
    let result: boolean = await this.userProposalService.deleteProposal(proposal.proposalId)
    if (result) {
      this.getAllProposals();
    }  
  }

  openProposalDetailsConfirmed(i: number) {
    this.proposalToModal = this.confirmedList[i];
    this.modalService.openModal("proposalDetails");
  }

  openProposalDetailsNoConfirmed(i: number) {
    this.proposalToModal = this.noConfirmedList[i];
    this.modalService.openModal("proposalDetails");
  }

  deleteProposalToModal() {
    this.proposalToModal = {
      proposalId: 0,
      proposalTitle: '',
      proposalDescription: '',
      proposalEstimatedTime: '',
      proposalWorkStyle: '',
      proposalStatus: '',
      proposalSendDate: null,
      proposalConfirmDate: null,
      userSeekerId: 0,
      seekerName: '',
      seekerProfilePhoto: '',
      seekerTitle: '',
      seekerCity: '',
      seekerCountry: '',
      userFreelancerId: 0,
      userFreelancerName: ''
    }
  }

}
