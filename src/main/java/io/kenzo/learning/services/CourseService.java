package io.kenzo.learning.services;

import io.kenzo.learning.exception.ModelNotFoundException;
import io.kenzo.learning.model.Course;
import io.kenzo.learning.model.User;
import io.kenzo.learning.repositories.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class CourseService {
    public final CourseRepository courseRepository;

    public List<Course> allUsers() {
        return courseRepository.findAll();
    }

    public Course findById(UUID id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new ModelNotFoundException("User not found"));
    }

    public Course store(Course course) {
        return courseRepository.save(course);
    }

}
