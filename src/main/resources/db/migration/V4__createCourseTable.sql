
CREATE TABLE courses (
                         id UUID NOT NULL DEFAULT gen_random_uuid(),
                         is_active BOOLEAN NOT NULL DEFAULT TRUE,
                         created_at TIMESTAMP WITHOUT TIME ZONE,
                         updated_at TIMESTAMP WITHOUT TIME ZONE,
                         course_name VARCHAR(150) NOT NULL,
                         course_code VARCHAR(50) NOT NULL UNIQUE,
                         description TEXT,
                         duration INTEGER,
                         credits INTEGER,
                         instructor VARCHAR(150),
                         department VARCHAR(150),
                         tuition_fee NUMERIC(12, 2)
                     );
