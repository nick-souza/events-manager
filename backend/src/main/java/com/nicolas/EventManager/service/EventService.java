package com.nicolas.EventManager.service;

import com.nicolas.EventManager.domain.Event;
import com.nicolas.EventManager.dto.EventDto;
import com.nicolas.EventManager.exception.BadRequestException;
import com.nicolas.EventManager.exception.NotFoundException;
import com.nicolas.EventManager.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventService {
    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event findEventById(Long id) throws NotFoundException {
        return this.eventRepository.findEventById(id).orElseThrow(() -> new NotFoundException("Event not found"));
    }

    public List<Event> findAllEvents() {
        return this.eventRepository.findAllByOrderByStartDateAsc();
    }

    public Event createEvent(EventDto data) throws BadRequestException {
        this.validatePrice(data.price());
        this.validateDates(data.startDate(), data.endDate());

        Event event = new Event(data);
        this.saveEvent(event);

        return event;
    }

    public void deleteEvent(Long id) throws NotFoundException {
        Event event = this.eventRepository.findEventById(id).orElseThrow(() -> new NotFoundException("Event not found"));
        this.eventRepository.delete(event);
    }

    private void saveEvent(Event event) {
        this.eventRepository.save(event);
    }

    private void validateDates(LocalDateTime startDate, LocalDateTime endDate) throws BadRequestException {
        LocalDateTime now = LocalDateTime.now();

        if (endDate.isBefore(now)) {
            throw new BadRequestException("End date must be in the future");
        }

        if (startDate.isAfter(endDate)) {
            throw new BadRequestException("Start date must be before end date");
        }
    }

    private void validatePrice(Double price) throws BadRequestException {
        if (price < 0) {
            throw new BadRequestException("Price must be greater than 0");
        }
    }
}
