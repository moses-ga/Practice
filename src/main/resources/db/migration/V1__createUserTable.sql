CREATE TABLE users
(
    id         UUID                        NOT NULL DEFAULT gen_random_uuid(),
    is_active  BOOLEAN                     NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP WITHOUT TIME ZONE,
    updated_at TIMESTAMP WITHOUT TIME ZONE,
    name       VARCHAR(255)                NOT NULL,
    email      VARCHAR(255)                NOT NULL,
    phone      VARCHAR(50),
    address    VARCHAR(255),
    CONSTRAINT pk_users PRIMARY KEY (id),
    CONSTRAINT uk_users_email UNIQUE (email)
);
