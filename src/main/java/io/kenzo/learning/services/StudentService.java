package io.kenzo.learning.services;

import io.kenzo.learning.exception.ModelNotFoundException;
import io.kenzo.learning.model.Student;
import io.kenzo.learning.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public List<Student> allStudents() {
        return studentRepository.findAll();
    }

    public Student findById(UUID id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ModelNotFoundException("Student not found"));
    }

    public Student store(Student student) {
        return studentRepository.save(student);
    }

    /** Copies the editable fields onto the stored record so unknown ids fail with 404 instead of inserting. */
    @Transactional
    public Student update(UUID id, Student changes) {
        Student student = findById(id);
        student.setName(changes.getName());
        student.setPhone(changes.getPhone());
        student.setEmail(changes.getEmail());
        student.setRegNumber(changes.getRegNumber());
        student.setIsActive(changes.getIsActive());
        return studentRepository.save(student);
    }

    public void delete(UUID id) {
        studentRepository.delete(findById(id));
    }

    public boolean emailExists(String email, UUID excludeId) {
        return excludeId == null
                ? studentRepository.countByEmail(email) > 0
                : studentRepository.countByEmailAndIdNot(email, excludeId) > 0;
    }

    public boolean regNumberExists(String regNumber, UUID excludeId) {
        return excludeId == null
                ? studentRepository.countByRegNumber(regNumber) > 0
                : studentRepository.countByRegNumberAndIdNot(regNumber, excludeId) > 0;
    }
}
