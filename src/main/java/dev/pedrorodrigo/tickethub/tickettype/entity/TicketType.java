package dev.pedrorodrigo.tickethub.tickettype.entity;

import dev.pedrorodrigo.tickethub.common.persistence.AuditableEntity;
import dev.pedrorodrigo.tickethub.event.entity.Event;
import dev.pedrorodrigo.tickethub.tickettype.enums.TicketTypeStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(
        name = "ticket_types",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_ticket_type_event_code",
                        columnNames = {"event_id", "code"}
                )
        }
)
public class TicketType extends AuditableEntity {

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 150, nullable = false)
    private String name;

    @Column(length = 50, nullable = false)
    private String code;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(nullable = false)
    private Integer totalQuantity;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 30)
    private TicketTypeStatus status = TicketTypeStatus.DRAFT;

    @Column(name = "sales_starts_at", nullable = false)
    private LocalDateTime salesStartsAt;

    @Column(name = "sales_ends_at", nullable = false)
    private LocalDateTime salesEndsAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

}
