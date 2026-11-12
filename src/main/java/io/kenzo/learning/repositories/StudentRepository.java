package io.kenzo.learning.repositories;

import io.kenzo.learning.model.Student;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

import java.util.UUID;

public interface StudentRepository extends DataTablesRepository<Student, UUID> {
}
