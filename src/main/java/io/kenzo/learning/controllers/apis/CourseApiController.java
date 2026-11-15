package io.kenzo.learning.controllers.apis;

import io.kenzo.learning.model.Course;
import io.kenzo.learning.model.Student;
import io.kenzo.learning.services.CourseService;
import io.kenzo.learning.utils.GeneralResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/course")
@RequiredArgsConstructor
public class CourseApiController {

    public final CourseService courseService;

    @GetMapping
    public List<Course> index() {
        return courseService.allUsers();
    }

    @GetMapping("/{id}")
    public Course show(@PathVariable("id") UUID id) {
        return courseService.findById(id);
    }

//    @PostMapping
//    public ResponseEntity<?> store(@RequestBody Course student) {
//        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.store(student));
//    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Course store(@RequestBody Course course) {
        return courseService.store(course);
    }
}
