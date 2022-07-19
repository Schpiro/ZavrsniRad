package com.bbzavrsni.zavrsni.service.interfaces;

import com.bbzavrsni.zavrsni.command.MessageCommand;
import com.bbzavrsni.zavrsni.model.dto.MessageDTO;
import com.bbzavrsni.zavrsni.model.pojo.Message;

import java.util.List;
import java.util.Optional;
import java.util.stream.DoubleStream;

public interface MessageService {
    List<MessageDTO> findAllByUser(String username);

    List<MessageDTO> findAllByGroup(Long groupId);

    Optional<MessageDTO> sendMessage(MessageCommand messageCommand, Long userId);
}
