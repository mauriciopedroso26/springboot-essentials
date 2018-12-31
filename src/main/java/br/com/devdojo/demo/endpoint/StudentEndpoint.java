package br.com.devdojo.demo.endpoint;

import br.com.devdojo.demo.error.ResourceNotFoundException;
import br.com.devdojo.demo.model.Student;
import br.com.devdojo.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;


@RestController
@RequestMapping("students")
public class StudentEndpoint {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentEndpoint(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping
    public ResponseEntity<?> findAll(Pageable pageable) {
        return new ResponseEntity<>(studentRepository.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/findAllByOrderByNameDesc")
    public ResponseEntity<?> findAllByOrderByNameDesc(Pageable pageable) {
        return new ResponseEntity<>(studentRepository.findAllByOrderByNameDesc(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable("id") Long id) {
        verifyIfStudentsExistis(id);

        Optional<Student> student = studentRepository.findById(id);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @GetMapping("/findByName/{name}")
    public ResponseEntity<?> findStudentByName(@PathVariable("name") String name) {
        return new ResponseEntity<>(studentRepository.findByNameIgnoreCaseContaining(name), HttpStatus.OK);
    }

    @PostMapping
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> save(@Valid @RequestBody Student student) {
        return new ResponseEntity<>(studentRepository.save(student), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        verifyIfStudentsExistis(id);

        studentRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Student student) {
        verifyIfStudentsExistis(student.getId());

        studentRepository.save(student);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    private void verifyIfStudentsExistis(Long id){
        Optional<Student> student = studentRepository.findById(id);
        if (!student.isPresent()) {
            throw new ResourceNotFoundException("Student not found for ID: " + id);
        }

    }
}
