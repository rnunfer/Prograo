package com.prograo.service;

import com.prograo.domain.Calification;
import com.prograo.domain.Project;
import com.prograo.dto.CalificationDTO;
import com.prograo.repository.CalificationRepository;
import com.prograo.repository.FreelancerRepository;
import com.prograo.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CalificationService {

    private final CalificationRepository calificationRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public CalificationService(CalificationRepository calificationRepository) {
        this.calificationRepository = calificationRepository;
    }

    public List<CalificationDTO> getAllCalification(Long id) {
        return this.calificationRepository.getAllCalification(id);
    }

    public void sendCalification(Map<Object, String> data, Long projectId) {
        Optional<Project> projectOptional = this.projectRepository.findById(projectId);
        if (projectOptional.isPresent()) {
            Project project = projectOptional.get();
            Optional<Calification> calificationOptional = this.calificationRepository.getCalificationByProject(project);
            if (calificationOptional.isPresent()) {
                Calification calification = calificationOptional.get();
                calification.setDescription(data.get("content"));
                calification.setNote(Double.valueOf(data.get("note")));
                calification.setDate(new Date());
                this.calificationRepository.save(calification);
            } else {
                Calification calification = new Calification();
                calification.setProject(project);
                calification.setDescription(data.get("content"));
                calification.setNote(Double.valueOf(data.get("note")));
                calification.setDate(new Date());
                this.calificationRepository.save(calification);
            }
        }
    }
}
