package io.kenzo.learning.repositories;

import io.kenzo.learning.model.Student;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

import java.util.UUID;

public interface StudentRepository extends DataTablesRepository<Student, UUID> {

    long countByEmail(String email);

    long countByEmailAndIdNot(String email, UUID id);

    long countByRegNumber(String regNumber);

    long countByRegNumberAndIdNot(String regNumber, UUID id);
}
