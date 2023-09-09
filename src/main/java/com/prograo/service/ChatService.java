package com.prograo.service;

import com.prograo.domain.Chat;
import com.prograo.domain.Project;
import com.prograo.dto.ChatDTO;
import com.prograo.repository.ChatRepository;
import com.prograo.repository.ProjectRepository;
import com.prograo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ChatService {

    private final ChatRepository chatRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;


    public ChatService(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    public List<ChatDTO> getAllChatFromProject(Long projectId) {
        return this.chatRepository.getAllChatFromProject(projectId);
    }

    public boolean sendMessageToProject(Long projectId, Long userId, String message) {
        boolean result = false;
        Optional<Project> projectOptional = this.projectRepository.findById(projectId);
        if (projectOptional.isPresent()) {
            Chat chat = new Chat();
            chat.setProject(projectOptional.get());
            chat.setMessage(message);
            chat.setSendDate(new Date());
            chat.setUser(this.userRepository.findById(userId).get());
            this.chatRepository.save(chat);
            result = true;
        }
        return result;
    }
}
