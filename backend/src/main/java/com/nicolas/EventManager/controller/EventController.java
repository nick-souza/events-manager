package com.nicolas.EventManager.controller;

import com.nicolas.EventManager.domain.Event;
import com.nicolas.EventManager.dto.EventDto;
import com.nicolas.EventManager.service.EventService;
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
    public ResponseEntity<Event> findEventById(@PathVariable Long id) throws Exception {
        Event event = this.eventService.findEventById(id);

        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<Event>> findAllEvents() {
        List<Event> events = this.eventService.findAllEvents();

        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Event> createEvent(@RequestBody EventDto data) throws Exception {
        Event newEvent = this.eventService.createEvent(data);

        return new ResponseEntity<>(newEvent, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Event> deleteEvent(@PathVariable Long id) throws Exception {
        this.eventService.deleteEvent(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
