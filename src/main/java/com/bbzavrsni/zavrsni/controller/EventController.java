package com.bbzavrsni.zavrsni.controller;

import com.bbzavrsni.zavrsni.ZavrsniApplication;
import com.bbzavrsni.zavrsni.command.EventCommand;
import com.bbzavrsni.zavrsni.model.dto.CommentDTO;
import com.bbzavrsni.zavrsni.model.dto.EventDTO;
import com.bbzavrsni.zavrsni.model.pojo.UserAuthentication;
import com.bbzavrsni.zavrsni.service.interfaces.CommentService;
import com.bbzavrsni.zavrsni.service.interfaces.EventService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("event")
@CrossOrigin(origins = "https://localhost:4200")
public class EventController {
    private static final Logger logger = LogManager.getLogger(ZavrsniApplication.class);

    private final EventService eventService;
    private final CommentService commentService;

    public EventController(EventService eventService, CommentService commentService) {
        this.eventService = eventService;
        this.commentService = commentService;
    }

    @GetMapping
    public List<EventDTO> getAllEvents() {
        return eventService.findAll();
    }

    @PostMapping
    public ResponseEntity<EventDTO> createEvent(@Valid @RequestBody final EventCommand eventCommand, Principal principal) {
        logger.info(principal);
        return eventService.createEvent(eventCommand, ((UserAuthentication) principal).getPrincipal().getUID())
                .map(
                        EventDTO -> ResponseEntity
                                .status(HttpStatus.CREATED)
                                .body(EventDTO)
                )
                .orElseGet(
                        () -> ResponseEntity
                                .status(HttpStatus.CONFLICT)
                                .build()
                );
    }

    @GetMapping
    @RequestMapping("/comments/{id}")
    public List<CommentDTO> getCommentsForEvent(@PathVariable("id") Long id) {
        return commentService.findCommentForEvent(id);
    }
}
