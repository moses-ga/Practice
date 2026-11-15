package io.kenzo.learning.controllers.apis;

import io.kenzo.learning.model.Course;
import io.kenzo.learning.model.Department;
import io.kenzo.learning.services.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/department")
@RequiredArgsConstructor
public class DepartmentApiController {
    public final DepartmentService departmentService;

    @GetMapping
    public List<Department> index() {
        return departmentService.all();
    }

    @GetMapping("/{id}")
    public Department show(@PathVariable("id") UUID id) {
        return departmentService.findById(id);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Department store(@RequestBody Department course) {
        return departmentService.store(course);
    }
}
