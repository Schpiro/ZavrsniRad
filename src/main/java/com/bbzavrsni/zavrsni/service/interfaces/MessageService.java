package com.bbzavrsni.zavrsni.service.interfaces;

import com.bbzavrsni.zavrsni.model.dto.MessageDTO;

import java.util.List;

public interface MessageService {
    List<MessageDTO> findAllByUser(String username);
}
