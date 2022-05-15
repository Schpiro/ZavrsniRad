package com.bbzavrsni.zavrsni.service;

import com.bbzavrsni.zavrsni.model.dto.EventDTO;
import com.bbzavrsni.zavrsni.model.pojo.Event;
import com.bbzavrsni.zavrsni.repository.interfaces.EventRepository;
import com.bbzavrsni.zavrsni.service.interfaces.EventService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public List<EventDTO> findAll() {
        return eventRepository.findAll().stream().map(this::mapEventToDTO).collect(Collectors.toList());
    }

    private EventDTO mapEventToDTO(final Event event){
        return new EventDTO(event.getCreator().getUsername(),event.getEventDetails(),event.getCreationDate());
    }
}
