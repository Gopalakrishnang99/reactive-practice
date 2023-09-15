package com.chaos.reactive.practice.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.chaos.reactive.practice.dto.StudentDto;
import com.chaos.reactive.practice.service.StudentService;
import com.chaos.reactive.practice.util.TestUtil;

import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
@WebFluxTest(StudentController.class)
public class StudentControllerTest {

    @Autowired
    private WebTestClient webClient;

    @MockBean
    private StudentService studentService;

    @Test
    @DisplayName("Test to save a new student record")
    public void testToSaveNewStudent() {
        Mockito.when(studentService.saveStudent(Mockito.anyString(), Mockito.anyString()))
                .thenAnswer(i -> Mono.just(TestUtil.getDummyStudent(i.getArgument(0), i.getArgument(1))));
        webClient.post().uri("/students").body(Mono.just(TestUtil.getDummyStudentDto("John", "Doe")), StudentDto.class)
                .exchange()
                .expectStatus().isCreated();
    }

    @Test
    @DisplayName("Test to get a student with Id")
    public void testToGetStudent() {
        Mockito.when(studentService.getStudent(Mockito.anyString()))
                .thenAnswer(i -> Mono.just(TestUtil.getDummyStudent(i.getArgument(0))));
        webClient.get().uri("/students/abcd-efg").exchange().expectStatus().isOk();
    }

    @Test
    @DisplayName("Test to delete a student")
    public void testToDeleteAStudent() {
        Mockito.doNothing().when(studentService).deleteStudent(Mockito.anyString());
        webClient.delete().uri("/students/abcd-efg").exchange().expectStatus().isNoContent();
    }

    @Test
    @DisplayName("Test to update a student record")
    public void testToUpdateAStudentRecord() {
        Mockito.when(studentService.updateStudentDetails(Mockito.anyString(), Mockito.any(StudentDto.class)))
                .thenAnswer(i -> Mono.just(TestUtil.getDummyStudent(i.getArgument(0))));
        webClient.put().uri("/students/abcd-efg")
                .body(Mono.just(TestUtil.getDummyStudentDto("John II", "Doe")), StudentDto.class).exchange()
                .expectStatus().isOk();
    }
}
