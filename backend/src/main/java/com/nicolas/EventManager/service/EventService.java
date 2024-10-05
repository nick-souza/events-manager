package com.nicolas.EventManager.service;

import com.nicolas.EventManager.domain.Event;
import com.nicolas.EventManager.dto.EventDto;
import com.nicolas.EventManager.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event findEventById(Long id) throws Exception {
        return this.eventRepository.findEventById(id).orElseThrow(() -> new Exception("Event not found"));
    }

    public List<Event> findAllEvents() {
        return this.eventRepository.findAllByOrderByStartDateAsc();
    }

    public Event createEvent(EventDto data) {
        Event event = new Event(data);
        this.saveEvent(event);

        return event;
    }

    public void deleteEvent(Long id) throws Exception {
        Event event = this.eventRepository.findEventById(id).orElseThrow(() -> new Exception("Event not found"));
        this.eventRepository.delete(event);
    }

    private void saveEvent(Event event) {
        this.eventRepository.save(event);
    }
}
