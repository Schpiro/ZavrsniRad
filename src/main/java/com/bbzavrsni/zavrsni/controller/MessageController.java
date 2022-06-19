package com.bbzavrsni.zavrsni.controller;

import com.bbzavrsni.zavrsni.model.dto.MessageDTO;
import com.bbzavrsni.zavrsni.model.pojo.UserAuthentication;
import com.bbzavrsni.zavrsni.service.interfaces.MessageService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("message")
@CrossOrigin(origins = "http://localhost:4200")
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService){this.messageService = messageService;}

    @GetMapping
    public List<MessageDTO> getAllMessages(Principal principal){
        return messageService.findAllByUser(((UserAuthentication) principal).getPrincipal().getUsername());
    }
}
