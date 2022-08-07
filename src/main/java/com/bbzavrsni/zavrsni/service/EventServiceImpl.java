package com.bbzavrsni.zavrsni.service;

import com.bbzavrsni.zavrsni.command.EventCommand;
import com.bbzavrsni.zavrsni.model.dto.EventDTO;
import com.bbzavrsni.zavrsni.model.pojo.Event;
import com.bbzavrsni.zavrsni.model.pojo.User;
import com.bbzavrsni.zavrsni.repository.interfaces.EventRepository;
import com.bbzavrsni.zavrsni.service.interfaces.EventService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public EventServiceImpl(EventRepository eventRepository, EntityManager entityManager) {
        this.eventRepository = eventRepository;
        this.entityManager = entityManager;
    }

    @Override
    public List<EventDTO> findAll() {
        return eventRepository.findAll().stream().map(this::mapEventToDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<EventDTO> createEvent(EventCommand eventCommand, Long uid) {
        return Optional.of(mapEventToDTO(eventRepository.save(mapCommandToEvent(eventCommand))));
    }

    private EventDTO mapEventToDTO(final Event event) {
        return new EventDTO(event.getId(),event.getCreator().getUsername(), event.getEventDetails(), event.getCreationDate().toString());
    }

    private Event mapCommandToEvent(final EventCommand eventCommand) {
        return new Event(entityManager.getReference(User.class, eventCommand.getCreator()),
                eventCommand.getDetails()
        );
    }
}
