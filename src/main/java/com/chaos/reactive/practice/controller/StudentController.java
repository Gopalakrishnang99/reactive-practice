package com.chaos.reactive.practice.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chaos.reactive.practice.dto.StudentDto;
import com.chaos.reactive.practice.model.Student;
import com.chaos.reactive.practice.service.StudentService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public Mono<ResponseEntity<Student>> saveNewStudent(@RequestBody StudentDto student) {
        Mono<Student> newStudent = studentService.saveStudent(student.getFirstName(), student.getLastName());
        return newStudent.map(s -> ResponseEntity.created(URI.create("/students/" + s.getId())).body(s));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Student>> getStudentById(@PathVariable String id) {
        return studentService.getStudent(id).map(s -> ResponseEntity.ok().body(s));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable String id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Student>> updateStudent(@PathVariable String id, @RequestBody StudentDto student) {
        return studentService.updateStudentDetails(id, student).map(s -> ResponseEntity.ok().body(s));
    }
}
