package com.chaos.reactive.practice.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.chaos.reactive.practice.dto.StudentDto;
import com.chaos.reactive.practice.model.Student;
import com.chaos.reactive.practice.util.StudentUtil;

import reactor.core.publisher.Mono;

@Service
public class StudentServiceImpl implements StudentService {

    /**
     * This methos is used to save a new student record
     * 
     * @param firstName
     * @param lastName
     * 
     * @return saved student record
     */
    @Override
    public Mono<Student> saveStudent(String firstName, String lastName) {
        String id = UUID.randomUUID().toString();
        Student savedStudent = new Student();
        savedStudent.setFirstName(firstName);
        savedStudent.setLastName(lastName);
        savedStudent.setId(id);
        return Mono.just(savedStudent);
    }

    /**
     * Method to retrieve a student record using student id
     * 
     * @param id - The id of the student
     * 
     * @return The fetched student record
     */
    @Override
    public Mono<Student> getStudent(String id) {
        Student student = new Student();
        student.setFirstName("FirstName");
        student.setLastName("LastName");
        student.setId(id);
        return Mono.just(student);
    }

    /**
     * Method to delete a student record
     * 
     * @param id - Id of the student to be deleted
     * 
     */
    @Override
    public void deleteStudent(String id) {
        // Deleting student from db
        return;
    }

    /**
     * Method to update a student's details
     * 
     * @param id - Id of the student to be updated
     * @param updatedStudent - Student object containing the updated details
     * 
     * @return The updated student record
     */
    @Override
    public Mono<Student> updateStudentDetails(String id, StudentDto updatedStudent) {
        Student student = StudentUtil.getDummyStudent(id);
        if (updatedStudent.getFirstName() != null)
            student.setFirstName(updatedStudent.getFirstName());
        if (updatedStudent.getLastName() != null)
            student.setLastName(updatedStudent.getLastName());
        return Mono.just(student);
    }

}
