package org.rogarithm.notifyevent.repository;

import org.rogarithm.notifyevent.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {

}
