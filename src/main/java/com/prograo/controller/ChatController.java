package com.prograo.controller;

import com.prograo.dto.ChatDTO;
import com.prograo.service.ChatService;
import com.prograo.service.SecurityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/chats")
public class ChatController {

    private final ChatService chatService;

    @Autowired
    private SecurityService securityService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping(value = "/chats-from-project/{projectId}")
    public List<ChatDTO> getAllChatFromProject(@RequestHeader("userAuthId") String userAuthId, @PathVariable("projectId") Long projectId) {
        List<ChatDTO> list = new ArrayList<>();
        if (this.securityService.checkPermissions(userAuthId, "FS")) {
            list = this.chatService.getAllChatFromProject(projectId);
        }
        return list;
    }

    @PostMapping(value = "/send-message-to/{projectId}/by/{userId}")
    public boolean sendMessageToProject(@RequestHeader("userAuthId") String userAuthId, @RequestBody String message, @PathVariable("projectId") Long projectId, @PathVariable("userId") Long userId) {
        boolean result = false;
        if (this.securityService.checkPermissions(userAuthId, "FS")) {
            result = this.chatService.sendMessageToProject(projectId, userId, message);
        }
        return result;
    }
}
