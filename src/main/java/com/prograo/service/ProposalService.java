package com.prograo.service;

import com.prograo.domain.Freelancer;
import com.prograo.domain.Proposal;
import com.prograo.domain.Seeker;
import com.prograo.dto.ProposalDTO;
import com.prograo.dto.ProposalDTOImpl;
import com.prograo.repository.FreelancerRepository;
import com.prograo.repository.ProposalRepository;
import com.prograo.repository.SeekerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.rmi.server.LogStream.log;

@Service
public class ProposalService {

    private final ProposalRepository proposalRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private SeekerRepository seekerRepository;

    @Autowired
    private FreelancerRepository freelancerRepository;

    @Autowired
    private ProjectService projectService;

    public ProposalService(ProposalRepository proposalRepository) {
        this.proposalRepository = proposalRepository;
    }

    public List<ProposalDTO> getAllProposalDTO(Long userId) {
        List<ProposalDTO> proposalDTOList = new ArrayList<>();
        String userType = this.userService.getUserType(userId);
        if(userType.equals("administrator")) {
            proposalDTOList = this.proposalRepository.getAllProposals();
        } else if(userType.equals("freelancer") || userType.equals("seeker")) {
            proposalDTOList = this.proposalRepository.getProposalsByUserId(userId);
        }
        return proposalDTOList;
    }

    public ProposalDTO getProposalDTObyProposalId(Long proposalId) {
        ProposalDTO p = new ProposalDTOImpl();
        Optional<Proposal> proposalDTOOptional = this.proposalRepository.findById(proposalId);
        if (proposalDTOOptional.isPresent()) {
            p = this.proposalRepository.getProposalById(proposalId);
        }
        return p;
    }

    public boolean editProposal(ProposalDTO proposalDTO) {
        boolean result = false;
        Optional<Proposal> proposalOptional = this.proposalRepository.findById(proposalDTO.getProposalId());
        if(proposalOptional.isPresent()) {
            Proposal p = proposalOptional.get();
            p.setTitle(proposalDTO.getProposalTitle());
            p.setDescription(proposalDTO.getProposalDescription());
            p.setEstimatedTime(proposalDTO.getProposalEstimatedTime());
            p.setWorkStyle((proposalDTO.getProposalWorkStyle()));
            p.setSendDate((proposalDTO.getProposalSendDate()));
            p.setConfirmDate(proposalDTO.getProposalConfirmDate());
            this.proposalRepository.save(p);
            result = true;
        }
        return result;
    }

    public boolean confirmProposal(Long proposalId, String newStatus) {
        boolean response = false;
        Optional<Proposal> optionalProposal = this.proposalRepository.findById(proposalId);
        if (optionalProposal.isPresent() && (newStatus.equals("accepted") || newStatus.equals("rejected"))) {
            Proposal proposal = optionalProposal.get();
            proposal.setStatus(newStatus);
            proposal.setConfirmDate(new Date());
            this.proposalRepository.save(proposal);
            if (newStatus.equals("accepted"))
                this.projectService.newProjectByProposal(proposal);
            response = true;
        }
        return response;
    }

    public boolean deleteProposal(Long proposalId) {
        boolean response = false;
        this.proposalRepository.deleteById(proposalId);
        if(this.proposalRepository.findById(proposalId).isEmpty()) {
            response = true;
        }
        return response;
    }

    public boolean sendProposal(Map<Object, String> data, Long userId) {
        boolean result = false;
        Optional<Seeker> seekerOptional = this.seekerRepository.getSeekerByUserId(userId);
        Optional<Freelancer> freelancerOptional = this.freelancerRepository.findById(Long.valueOf(data.get("freelancerId")));
        if (seekerOptional.isPresent() && freelancerOptional.isPresent()) {
            Seeker seeker = seekerOptional.get();
            Freelancer freelancer = freelancerOptional.get();
            Proposal newProposal = new Proposal();
            newProposal.setTitle(data.get("title"));
            newProposal.setDescription(data.get("description"));
            newProposal.setEstimatedTime(data.get("estimatedTime"));
            newProposal.setWorkStyle(data.get("workStyle"));
            newProposal.setSendDate(new Date());
            newProposal.setStatus("waiting");
            newProposal.setFreelancer(freelancer);
            newProposal.setSeeker(seeker);
            this.proposalRepository.save(newProposal);
            return true;
        }
        return result;
    }

}
