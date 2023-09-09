package com.prograo.service;

import com.prograo.domain.*;
import com.prograo.dto.FreelancerSkillDTO;
import com.prograo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SkillService {

    private final SkillRepository skillRepository;

    @Autowired
    private FreelancerSkillRepository freelancerSkillRepository;

    @Autowired
    private FreelancerRepository freelancerRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public SkillService(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    public List<Skill> getAllSkills() {
        return this.skillRepository.findAll();
    }

    public List<FreelancerSkillDTO> getAllFreelancerSkill(Long userId) {
        List<FreelancerSkillDTO> result = new ArrayList<>();
        Optional<Freelancer> freelancerOptional = this.freelancerRepository.getFreelancerByUserId(userId);
        if (freelancerOptional.isPresent()) {
            result = this.freelancerSkillRepository.getAllFreelancerSkill(freelancerOptional.get().getId());
        }
        return result;
    }

    public boolean addSkillToFreelancer(Long userId, Long skillId) {
        boolean result = false;
        Optional<Freelancer> freelancerOptional = this.freelancerRepository.getFreelancerByUserId(userId);
        Optional<Skill> skillOptional = this.skillRepository.findById(skillId);
        if (freelancerOptional.isPresent() && skillOptional.isPresent()) {
            FreelancerSkill freelancerSkill = new FreelancerSkill();
            freelancerSkill.setFreelancer(freelancerOptional.get());
            freelancerSkill.setSkill(skillOptional.get());
            freelancerSkill.setOutstanding(false);
            this.freelancerSkillRepository.save(freelancerSkill);
            this.changeUser(userId);
            result = true;
        }
        return result;
    }

    public boolean removeSkillOfFreelancer(Long userId, Long skillId) {
        boolean result = false;
        Optional<Freelancer> freelancerOptional = this.freelancerRepository.getFreelancerByUserId(userId);
        Optional<Skill> skillOptional = this.skillRepository.findById(skillId);
        if (freelancerOptional.isPresent() && skillOptional.isPresent()) {
            Optional<FreelancerSkill> freelancerSkillOptional = this.freelancerSkillRepository.getFreelancerSkillByFreelancerIdAndSkillId(freelancerOptional.get().getId(), skillOptional.get().getId());
            if (freelancerSkillOptional.isPresent()) {
                this.freelancerSkillRepository.deleteById(freelancerSkillOptional.get().getId());
                this.changeUser(userId);
                result = true;
            }
        }
        return result;
    }

    public boolean changeOutstandingSkillOfFreelancer(Long userId, Long skillId) {
        boolean result = false;
        Optional<Freelancer> freelancerOptional = this.freelancerRepository.getFreelancerByUserId(userId);
        Optional<Skill> skillOptional = this.skillRepository.findById(skillId);
        if (freelancerOptional.isPresent() && skillOptional.isPresent()) {
            Optional<FreelancerSkill> freelancerSkillOptional = this.freelancerSkillRepository.getFreelancerSkillByFreelancerIdAndSkillId(freelancerOptional.get().getId(), skillOptional.get().getId());
            if (freelancerSkillOptional.isPresent()) {
                FreelancerSkill freelancerSkill = freelancerSkillOptional.get();
                freelancerSkill.setOutstanding(!freelancerSkill.isOutstanding());
                this.freelancerSkillRepository.save(freelancerSkill);
                this.changeUser(userId);
                result = true;
            }
        }
        return result;
    }

    public void changeUser(Long userId) {
        User user = this.userRepository.findById(userId).get();
        user.setStatus(this.userService.setUserStatus(user));
        this.userRepository.save(user);

    }

    public boolean addSkillToProject(Long skillId, Long projectId) {
        boolean result = false;
        Optional<Skill> skillOptional = this.skillRepository.findById(skillId);
        Optional<Project> projectOptional = this.projectRepository.findById(projectId);
        if (skillOptional.isPresent() && projectOptional.isPresent()) {
            Project project = projectOptional.get();
            Set<Skill> setSkill = project.getSkills();
            setSkill.add(skillOptional.get());
            project.setSkills(setSkill);
            this.projectRepository.save(project);
            result = true;
        }
        return result;
    }

    public boolean removeSkillFromProject(Long skillId, Long projectId) {
        boolean result = false;
        Optional<Skill> skillOptional = this.skillRepository.findById(skillId);
        Optional<Project> projectOptional = this.projectRepository.findById(projectId);
        if (skillOptional.isPresent() && projectOptional.isPresent()) {
            Project project = projectOptional.get();
            Set<Skill> setSkill = project.getSkills();
            setSkill.removeIf(skill -> skill.getId().equals(skillId));
            project.setSkills(setSkill);
            this.projectRepository.save(project);
            result = true;
        }
        return result;
    }
}
