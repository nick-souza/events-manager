package com.nicolas.EventManager.repositories;

import com.nicolas.EventManager.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {

    Optional<Event> findEventById(long id);

    List<Event> findAllByOrderByStartDateAsc();
}
