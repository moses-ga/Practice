CREATE TABLE students (
    id UUID NOT NULL DEFAULT gen_random_uuid(),
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP WITHOUT TIME ZONE,
    updated_at TIMESTAMP WITHOUT TIME ZONE,
    name VARCHAR(255) NOT NULL,
    phone VARCHAR(50),
    email VARCHAR(255) NOT NULL,
    registration_number VARCHAR(50) NOT NULL,
    CONSTRAINT pk_students PRIMARY KEY (id),
    CONSTRAINT uk_students_email UNIQUE (email),
    CONSTRAINT uk_students_registration_number UNIQUE (registration_number)
);
