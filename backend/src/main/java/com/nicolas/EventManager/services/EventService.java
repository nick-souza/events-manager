package com.nicolas.EventManager.services;

import com.nicolas.EventManager.domain.Event;
import com.nicolas.EventManager.dtos.EventDto;
import com.nicolas.EventManager.dtos.EventResponseDto;
import com.nicolas.EventManager.exception.BadRequestException;
import com.nicolas.EventManager.exception.NotFoundException;
import com.nicolas.EventManager.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {
    private final EventRepository eventRepository;
    private final ValidationService validationService;

    @Autowired
    public EventService(EventRepository eventRepository, ValidationService validationService) {
        this.eventRepository = eventRepository;
        this.validationService = validationService;
    }

    public EventResponseDto findEventById(Long id) throws NotFoundException {
        Event event = this.eventRepository.findEventById(id).orElseThrow(() -> new NotFoundException("Event not found"));
        return new EventResponseDto(event);
    }

    public List<EventResponseDto> findAllEvents() {
        List<Event> events = this.eventRepository.findAllByOrderByStartDateAsc();
        return events.stream().map(EventResponseDto::new).collect(Collectors.toList());
    }

    public EventResponseDto createEvent(EventDto data) throws BadRequestException {
        validationService.validatePrice(data.price());
        validationService.validateDates(data.getStartDate(), data.getEndDate());

        Event event = new Event(data);
        this.saveEvent(event);

        return new EventResponseDto(event);
    }

    public void deleteEvent(Long id) throws NotFoundException {
        Event event = this.eventRepository.findEventById(id).orElseThrow(() -> new NotFoundException("Event not found"));
        this.eventRepository.delete(event);
    }

    public EventResponseDto updateEvent(Long id, EventDto data) {
        if (!data.id().equals(id)) throw new BadRequestException("Route and object IDs do not match");

        validationService.validatePrice(data.price());
        validationService.validateDates(data.getStartDate(), data.getEndDate());

        Event event = this.eventRepository.findEventById(id).orElseThrow(() -> new NotFoundException("Event not found"));

        event.setTitle(data.title());
        event.setStartDate(data.getStartDate());
        event.setEndDate(data.getEndDate());
        event.setPrice(data.price());
        event.setStatus(data.status());

        this.saveEvent(event);

        return new EventResponseDto(event);
    }

    private void saveEvent(Event event) {
        this.eventRepository.save(event);
    }
}
