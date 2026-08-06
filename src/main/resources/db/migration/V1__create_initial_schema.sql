CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    email VARCHAR(150) NOT NULL,
    user_type VARCHAR(50) NOT NULL DEFAULT 'CUSTOMER',
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,

    CONSTRAINT uk_users_email UNIQUE (email),

    CONSTRAINT ck_users_user_type
        CHECK (user_type IN ('CUSTOMER', 'ORGANIZER', 'ADMIN'))
);

CREATE TABLE events (
    id BIGSERIAL PRIMARY KEY,
    organizer_id BIGINT NOT NULL,
    name VARCHAR(150) NOT NULL,
    description TEXT,
    event_status VARCHAR(30) NOT NULL DEFAULT 'DRAFT',
    starts_at TIMESTAMP NOT NULL,
    ends_at TIMESTAMP NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,

    CONSTRAINT fk_events_organizer
        FOREIGN KEY (organizer_id)
        REFERENCES users(id),

    CONSTRAINT ck_events_date_range
        CHECK(ends_at > starts_at),

    CONSTRAINT ck_events_status
        CHECK(event_status IN ('DRAFT', 'PUBLISHED', 'CANCELLED', 'FINISHED'))
);

CREATE TABLE ticket_types (
    id BIGSERIAL PRIMARY KEY,
    event_id BIGINT NOT NULL,
    name VARCHAR(150) NOT NULL,
    code VARCHAR(50) NOT NULL,
    price NUMERIC(10, 2) NOT NULL,
    total_quantity INTEGER NOT NULL,
    status VARCHAR(30) NOT NULL DEFAULT 'DRAFT',
    sales_starts_at TIMESTAMP NOT NULL,
    sales_ends_at TIMESTAMP NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,

    CONSTRAINT ck_ticket_types_total_quantity
        CHECK(total_quantity >= 0),

    CONSTRAINT ck_ticket_types_price
        CHECK(price >= 0),

    CONSTRAINT fk_ticket_types_event
        FOREIGN KEY (event_id)
        REFERENCES events(id),

    CONSTRAINT ck_ticket_types_sales_period
        CHECK(sales_ends_at > sales_starts_at),

    CONSTRAINT uk_ticket_type_event_code UNIQUE (event_id, code),

    CONSTRAINT ck_ticket_types_status
        CHECK(status IN ('DRAFT', 'SCHEDULED', 'AVAILABLE', 'SOLD_OUT', 'CANCELLED'))
);