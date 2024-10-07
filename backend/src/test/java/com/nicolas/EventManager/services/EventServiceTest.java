package com.nicolas.EventManager.services;

import com.nicolas.EventManager.domain.Event;
import com.nicolas.EventManager.domain.EventStatus;
import com.nicolas.EventManager.dtos.EventDto;
import com.nicolas.EventManager.dtos.EventResponseDto;
import com.nicolas.EventManager.exception.BadRequestException;
import com.nicolas.EventManager.exception.NotFoundException;
import com.nicolas.EventManager.repositories.EventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EventServiceTest {

    @Mock
    private EventRepository eventRepository;

    @Mock
    private ValidationService validationService;

    @InjectMocks
    private EventService eventService;

    private EventDto defaultEventDto;

    @BeforeEach
    public void setUp() {
        defaultEventDto = new EventDto(1L, "Event Title", "07/10/2024 09:10", "07/10/2024 10:10", 100.0, EventStatus.STARTED);
    }

    @Test
    @DisplayName("Should create an event")
    public void testCreateEvent() throws BadRequestException {
        Event event = new Event(defaultEventDto);

        doNothing().when(validationService).validatePrice(defaultEventDto.price());
        doNothing().when(validationService).validateDates(defaultEventDto.getStartDate(), defaultEventDto.getEndDate());

        when(eventRepository.save(any(Event.class))).thenReturn(event);

        EventResponseDto response = eventService.createEvent(defaultEventDto);

        assertNotNull(response);
        assertEquals("Event Title", response.getTitle());
    }

    @Test
    @DisplayName("Should throw a BadRequestException when creating an event with a negative price")
    public void testCreateEvent_NegativePrice() {
        EventDto eventDto = new EventDto(1L, "Event Title", "07/10/2024 09:10", "07/10/2024 10:10", -100.0, EventStatus.STARTED);

        doThrow(BadRequestException.class).when(validationService).validatePrice(anyDouble());

        assertThrows(BadRequestException.class, () -> eventService.createEvent(eventDto));
    }

    @Test
    @DisplayName("Should throw a BadRequestException when creating an event with an invalid start date")
    public void testCreateEvent_InvalidStartDate() {
        EventDto eventDto = new EventDto(1L, "Event Title", "Invalid Date", "07/10/2024 10:10", 100.0, EventStatus.STARTED);
        assertThrows(BadRequestException.class, () -> eventService.createEvent(eventDto));
    }

    @Test
    @DisplayName("Should update an event")
    public void testUpdateEvent() throws NotFoundException, BadRequestException {
        long eventId = 1L;

        Event event = new Event(defaultEventDto);

        doNothing().when(validationService).validatePrice(defaultEventDto.price());
        doNothing().when(validationService).validateDates(defaultEventDto.getStartDate(), defaultEventDto.getEndDate());

        when(eventRepository.findEventById(eventId)).thenReturn(Optional.of(event));
        when(eventRepository.save(any(Event.class))).thenReturn(event);

        EventResponseDto response = eventService.updateEvent(eventId, defaultEventDto);

        assertNotNull(response);
        assertEquals("Event Title", response.getTitle());
    }

    @Test
    @DisplayName("Should throw a BadRequestException when updating an event with a negative price")
    public void testUpdateEvent_NegativePrice() {
        EventDto eventDto = new EventDto(1L, "Event Title", "07/10/2024 09:10", "07/10/2024 10:10", -100.0, EventStatus.STARTED);

        doThrow(BadRequestException.class).when(validationService).validatePrice(anyDouble());

        assertThrows(BadRequestException.class, () -> eventService.updateEvent(1L, eventDto));
    }

    @Test
    @DisplayName("Should throw a BadRequestException when updating an event with an invalid date")
    public void testUpdateEvent_InvalidStartDate() {
        EventDto eventDto = new EventDto(1L, "Event Title", "Invalid Date", "07/10/2024 10:10", 100.0, EventStatus.STARTED);
        assertThrows(BadRequestException.class, () -> eventService.updateEvent(1L, eventDto));
    }

    @Test
    @DisplayName("Should delete an event")
    public void testDeleteEvent() throws NotFoundException {
        long eventId = 1L;

        Event event = new Event(defaultEventDto);

        when(eventRepository.findEventById(eventId)).thenReturn(Optional.of(event));
        doNothing().when(eventRepository).delete(event);

        eventService.deleteEvent(eventId);
        verify(eventRepository, times(1)).delete(event);
    }

    @Test
    @DisplayName("Should throw a NotFoundException when deleting an event that does not exist")
    public void testDeleteEvent_NotFound() {
        long eventId = 1L;

        when(eventRepository.findEventById(eventId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> eventService.deleteEvent(eventId));
    }

    @Test
    @DisplayName("Should find an event by id")
    public void testFindEventById() throws NotFoundException {
        long eventId = 1L;

        Event event = new Event(defaultEventDto);
        when(eventRepository.findEventById(eventId)).thenReturn(Optional.of(event));

        EventResponseDto response = eventService.findEventById(eventId);

        assertNotNull(response);
    }

    @Test
    @DisplayName("Should throw a NotFoundException when finding an event by id that does not exist")
    public void testFindEventById_NotFound() {
        long eventId = 1L;

        when(eventRepository.findEventById(eventId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> eventService.findEventById(eventId));
    }

    @Test
    @DisplayName("Should find all events")
    public void testFindAllEvents() {
        when(eventRepository.findAllByOrderByStartDateAsc()).thenReturn(List.of(new Event(defaultEventDto), new Event(defaultEventDto)));

        List<EventResponseDto> response = eventService.findAllEvents();

        assertEquals(2, response.size());
    }
}
