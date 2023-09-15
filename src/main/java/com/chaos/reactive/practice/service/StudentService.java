package com.chaos.reactive.practice.service;

import com.chaos.reactive.practice.dto.StudentDto;
import com.chaos.reactive.practice.model.Student;

import reactor.core.publisher.Mono;

public interface StudentService {
    
    Mono<Student> saveStudent(String firstName, String lastName);

    Mono<Student> getStudent(String id);

    void deleteStudent(String id);

    Mono<Student> updateStudentDetails(String id, StudentDto updatedStudent);

}
