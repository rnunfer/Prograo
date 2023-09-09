package com.prograo.controller;

import com.prograo.dto.ProposalDTO;
import com.prograo.dto.ProposalDTOImpl;
import com.prograo.service.ProposalService;
import com.prograo.service.SecurityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/proposals")
public class ProposalController {

    private final ProposalService proposalService;

    @Autowired
    private SecurityService securityService;

    public ProposalController(ProposalService proposalService) {
        this.proposalService = proposalService;
    }

    @GetMapping(value = "/from/{userId}")
    public List<ProposalDTO> getAllProposalDTO(@RequestHeader("userAuthId") String userAuthId, @PathVariable("userId") Long userId) {
        List<ProposalDTO> result = new ArrayList<>();
        if (this.securityService.checkPermissions(userAuthId, "FS", userId))
            result = this.proposalService.getAllProposalDTO(userId);
        return result;
    }

    @GetMapping(value = "/proposal-id/{proposalId}")
    public ProposalDTO getProposalDTOByProposalId(@RequestHeader("userAuthId") String userAuthId, @PathVariable("proposalId") Long proposalId) {
        ProposalDTO result = new ProposalDTOImpl();
        if (this.securityService.checkPermissions(userAuthId, "S"))
            result = this.proposalService.getProposalDTObyProposalId(proposalId);
        return result;
    }

    @PutMapping(value = "/edit")
    public boolean editProposal(@RequestHeader("userAuthId") String userAuthId, @RequestBody ProposalDTOImpl proposalDTO) {
        boolean result = false;
        if (this.securityService.checkPermissions(userAuthId, "S"))
            result = this.proposalService.editProposal(proposalDTO);
        return result;
    }

    @GetMapping(value = "/confirm/{status}/{proposalId}")
    public boolean confirmProposal(@RequestHeader("userAuthId") String userAuthId, @PathVariable("status") String status, @PathVariable("proposalId") Long proposalId) {
        boolean result = false;
        if (this.securityService.checkPermissions(userAuthId, "F"))
            result = this.proposalService.confirmProposal(proposalId, status);
        return result;
    }

    @DeleteMapping(value = "/delete/{proposalId}")
    public boolean deleteProposal(@RequestHeader("userAuthId") String userAuthId, @PathVariable("proposalId") Long proposalId) {
        boolean result = false;
        if (this.securityService.checkPermissions(userAuthId, "S"))
            result = this.proposalService.deleteProposal(proposalId);
        return result;
    }
    @PostMapping(value = "/send-proposal-by/{userId}")
    public boolean sendProposal(@RequestHeader("userAuthId") String userAuthId, @RequestBody Map<Object, String> data, @PathVariable("userId") Long userId) {
        boolean result = false;
        if (this.securityService.checkPermissions(userAuthId, "S")) {
            String title = data.get("title");
            result = this.proposalService.sendProposal(data, userId);
        }
        return result;
    }


}