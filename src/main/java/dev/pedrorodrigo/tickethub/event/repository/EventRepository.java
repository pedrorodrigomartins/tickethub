package dev.pedrorodrigo.tickethub.event.repository;

import dev.pedrorodrigo.tickethub.event.entity.Event;
import dev.pedrorodrigo.tickethub.event.enums.EventStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByOrganizerId(Long organizerId);

    List<Event> findByStatus(EventStatus status);
}
