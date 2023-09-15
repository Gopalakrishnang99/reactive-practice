package com.chaos.reactive.practice.util;

import java.util.UUID;

import com.chaos.reactive.practice.dto.StudentDto;
import com.chaos.reactive.practice.model.Student;

public class TestUtil {
    
    public static Student getDummyStudent(String firstName, String lastName){
        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setId(UUID.randomUUID().toString());
        return student;
    }

    public static Student getDummyStudent(String id){
        Student student = new Student();
        student.setFirstName("John");
        student.setLastName("Doe");
        student.setId(id);
        return student;
    }

    public static StudentDto getDummyStudentDto(String firstName, String lastName){
        StudentDto student = new StudentDto();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setId(UUID.randomUUID().toString());
        return student;
    }
}
