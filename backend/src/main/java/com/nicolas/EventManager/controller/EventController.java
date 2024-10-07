package com.nicolas.EventManager.controller;

import com.nicolas.EventManager.dto.EventDto;
import com.nicolas.EventManager.dto.EventResponseDto;
import com.nicolas.EventManager.exception.BadRequestException;
import com.nicolas.EventManager.exception.NotFoundException;
import com.nicolas.EventManager.service.EventService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventResponseDto> findEventById(@PathVariable Long id) throws NotFoundException {
        EventResponseDto event = this.eventService.findEventById(id);

        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<EventResponseDto>> findAllEvents() {
        List<EventResponseDto> events = this.eventService.findAllEvents();

        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<EventResponseDto> createEvent(@Valid @RequestBody EventDto data) throws BadRequestException {
        EventResponseDto newEvent = this.eventService.createEvent(data);

        return new ResponseEntity<>(newEvent, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventResponseDto> updateEvent(@PathVariable Long id, @Valid @RequestBody EventDto data) throws NotFoundException {
        EventResponseDto updatedEvent = this.eventService.updateEvent(id, data);

        return new ResponseEntity<>(updatedEvent, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EventResponseDto> deleteEvent(@PathVariable Long id) throws NotFoundException {
        this.eventService.deleteEvent(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
