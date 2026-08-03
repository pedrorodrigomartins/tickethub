package dev.pedrorodrigo.tickethub.user.entity;

import dev.pedrorodrigo.tickethub.common.persistence.AuditableEntity;
import dev.pedrorodrigo.tickethub.event.entity.Event;
import dev.pedrorodrigo.tickethub.user.enums.UserType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User extends AuditableEntity {

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 150, nullable = false)
    private String name;

    @Column(name = "password_hash", nullable = false, length = 50)
    private String passwordHash;

    @Email
    @Column(length = 200, nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_type", nullable = false)
    private UserType userType = UserType.CUSTOMER;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

}
