package io.kenzo.learning.controllers.apis;

import io.kenzo.learning.model.Student;
import io.kenzo.learning.repositories.StudentRepository;
import io.kenzo.learning.services.StudentService;
import io.kenzo.learning.utils.DataTablesUtils;
import io.kenzo.learning.utils.GeneralResponse;
import io.kenzo.learning.utils.ValidationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/student")
public class StudentApiController {

    private final StudentService studentService;
    private final StudentRepository studentRepository;
    private final ValidationUtils validationUtils;

    /** All students as a plain JSON array. */
    @GetMapping
    public List<Student> index() {
        return studentService.allStudents();
    }

    /** Server-side DataTables source, same contract as /api/users/list. */
    @GetMapping("/list")
    public DataTablesOutput<Student> list(DataTablesInput input) {
        return DataTablesUtils.findAll(studentRepository, input);
    }

    @GetMapping("/{id}")
    public Student show(@PathVariable("id") UUID id) {
        return studentService.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> store(@RequestBody Student student) {
        ResponseEntity<Map<String, Object>> invalid = validationUtils.validateAndBuildResponse(student);
        if (invalid != null) {
            return invalid;
        }
        ResponseEntity<GeneralResponse> duplicate = checkDuplicates(student, null);
        if (duplicate != null) {
            return duplicate;
        }
        student.setId(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.store(student));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") UUID id, @RequestBody Student student) {
        ResponseEntity<Map<String, Object>> invalid = validationUtils.validateAndBuildResponse(student);
        if (invalid != null) {
            return invalid;
        }
        ResponseEntity<GeneralResponse> duplicate = checkDuplicates(student, id);
        if (duplicate != null) {
            return duplicate;
        }
        return ResponseEntity.ok(studentService.update(id, student));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GeneralResponse> delete(@PathVariable("id") UUID id) {
        studentService.delete(id);
        return ResponseEntity.ok(new GeneralResponse(1, "Student is deleted successfully"));
    }

    /** 400 with a message when the email or registration number is already taken by another student. */
    private ResponseEntity<GeneralResponse> checkDuplicates(Student student, UUID excludeId) {
        if (studentService.emailExists(student.getEmail(), excludeId)) {
            return ResponseEntity.badRequest().body(new GeneralResponse(0, "Email already exists"));
        }
        if (studentService.regNumberExists(student.getRegNumber(), excludeId)) {
            return ResponseEntity.badRequest().body(new GeneralResponse(0, "Registration number already exists"));
        }
        return null;
    }
}
