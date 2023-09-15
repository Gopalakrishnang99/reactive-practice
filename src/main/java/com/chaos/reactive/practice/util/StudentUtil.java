package com.chaos.reactive.practice.util;

import java.util.UUID;

import com.chaos.reactive.practice.model.Student;

public class StudentUtil {
    
    public static Student getDummyStudent(String id){
        Student student = new Student();
        student.setFirstName("FirstName");
        student.setLastName("LastName");
        student.setId(id);
        return student;
    }

    public static Student getDummyStudent(){
        Student student = new Student();
        student.setFirstName("FirstName");
        student.setLastName("LastName");
        student.setId(UUID.randomUUID().toString());
        return student;
    }
}
