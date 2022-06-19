package com.bbzavrsni.zavrsni.service;

import com.bbzavrsni.zavrsni.model.dto.MessageDTO;
import com.bbzavrsni.zavrsni.model.pojo.Message;
import com.bbzavrsni.zavrsni.repository.interfaces.MessageRepository;
import com.bbzavrsni.zavrsni.service.interfaces.MessageService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;

    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public List<MessageDTO> findAllByUser(String username) {
        return messageRepository.findAllByRecipient_Username(username).stream().map(this::mapMessageToDTO).collect(Collectors.toList());
    }

    private MessageDTO mapMessageToDTO(Message message) {
        return new MessageDTO(message.getCreator().getUsername(),message.getCreateDate(),message.getMessageBody());
    }

}