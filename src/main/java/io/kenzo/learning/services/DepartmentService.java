package io.kenzo.learning.services;

import io.kenzo.learning.exception.ModelNotFoundException;
import io.kenzo.learning.model.Course;
import io.kenzo.learning.model.Department;
import io.kenzo.learning.model.User;
import io.kenzo.learning.repositories.CourseRepository;
import io.kenzo.learning.repositories.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class DepartmentService {
    public final DepartmentRepository departmentRepository;

    public List<Department> all() {
        return departmentRepository.findAll();
    }

    public Department findById(UUID id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new ModelNotFoundException("User not found"));
    }

    public Department store(Department department) {
        return departmentRepository.save(department);
    }

}
