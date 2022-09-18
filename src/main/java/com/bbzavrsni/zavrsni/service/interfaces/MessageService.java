package com.bbzavrsni.zavrsni.service.interfaces;

import com.bbzavrsni.zavrsni.command.MessageCommand;
import com.bbzavrsni.zavrsni.model.dto.MessageDTO;

import java.util.List;
import java.util.Optional;

public interface MessageService {
    List<MessageDTO> findAllByUser(Long userId);

    List<MessageDTO> getConversationWithUser(Long userId, Long senderId);

    List<MessageDTO> findAllByGroup(Long groupId);

    Optional<MessageDTO> sendMessage(MessageCommand messageCommand, Long userId);
}
