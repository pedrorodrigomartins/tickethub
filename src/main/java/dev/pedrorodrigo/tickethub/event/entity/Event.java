package dev.pedrorodrigo.tickethub.event.entity;

import dev.pedrorodrigo.tickethub.common.persistence.AuditableEntity;
import dev.pedrorodrigo.tickethub.event.enums.EventStatus;
import dev.pedrorodrigo.tickethub.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "events")
public class Event extends AuditableEntity {

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "event_status", nullable = false, length = 30)
    private EventStatus eventStatus = EventStatus.DRAFT;

    @Column(name = "starts_at", nullable = false)
    private LocalDateTime startsAt;

    @Column(name = "ends_at", nullable = false)
    private LocalDateTime endsAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organizer_id", nullable = false)
    private User organizer;

}
