package io.kenzo.learning.repositories;

import io.kenzo.learning.model.Course;
import io.kenzo.learning.model.Department;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

import java.util.UUID;

    public interface DepartmentRepository extends DataTablesRepository<Department, UUID> {

    }
