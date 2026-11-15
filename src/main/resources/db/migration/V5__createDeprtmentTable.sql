
CREATE TABLE departments (
                             id UUID PRIMARY KEY,
                             is_active BOOLEAN NOT NULL DEFAULT TRUE,
                             created_at TIMESTAMP,
                             updated_at TIMESTAMP,

                             department_name VARCHAR(150) NOT NULL,
                             department_code VARCHAR(50) NOT NULL UNIQUE,
                             description TEXT,
                             office_location VARCHAR(150),
                             email VARCHAR(150)
);
