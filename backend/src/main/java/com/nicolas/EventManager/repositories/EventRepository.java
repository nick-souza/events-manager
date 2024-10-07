package com.nicolas.EventManager.repositories;

import com.nicolas.EventManager.domain.Event;
import com.nicolas.EventManager.domain.EventStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {

    Optional<Event> findEventById(long id);

    List<Event> findAllByStatus(EventStatus status);

    List<Event> findAllByOrderByStartDateAsc();

    List<Event> findByStartDate(LocalDateTime startDate);

    List<Event> findByStartDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}
