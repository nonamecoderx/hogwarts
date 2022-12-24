package com.example.homework_hogwards;

import com.example.homework_hogwards.controller.StudentController;
import com.example.homework_hogwards.model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudentControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private StudentController userController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() {
        assertThat(userController).isNotNull();
    }

    @Test
    public void getAllStudentsTest() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(
                "http://localhost:" + port + "/student",
                String.class
        );
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }


    @Test
    public void getStudentTest() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(
                "http://localhost:" + port + "/student/1",
                String.class
        );
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}