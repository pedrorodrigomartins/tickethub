package dev.pedrorodrigo.tickethub.tickettype.repository;

import dev.pedrorodrigo.tickethub.tickettype.entity.TicketType;
import dev.pedrorodrigo.tickethub.tickettype.enums.TicketTypeStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketTypeRepository extends JpaRepository<TicketType, Long> {

    List<TicketType> findByEventId(Long eventId);

    List<TicketType> findByStatus(TicketTypeStatus status);
}
