package com.bbzavrsni.zavrsni.service.interfaces;

import com.bbzavrsni.zavrsni.command.EventCommand;
import com.bbzavrsni.zavrsni.model.dto.EventDTO;

import java.util.List;
import java.util.Optional;
import java.util.stream.DoubleStream;

public interface EventService {
    List<EventDTO> findAll();

    Optional<EventDTO> createEvent(EventCommand eventCommand, Long uid);
}
