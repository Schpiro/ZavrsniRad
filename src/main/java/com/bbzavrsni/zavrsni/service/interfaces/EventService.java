package com.bbzavrsni.zavrsni.service.interfaces;

import com.bbzavrsni.zavrsni.model.dto.EventDTO;

import java.util.List;

public interface EventService {
    List<EventDTO> findAll();

}
