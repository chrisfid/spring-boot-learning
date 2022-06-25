package com.example.demo.student;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository underTest;

    // For each test delete everyone (to have a clear state)
    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void itShouldFindStudentByEmail() {
        // given
        String email = "jamila@gmail.com";
        Student student = new Student(
                "Jamila", email, LocalDate.of(2001, Month.JANUARY, 8)
        );
        underTest.save(student);

        // when
        Optional<Student> studentByEmail = underTest.findStudentByEmail(email);
        boolean expected = studentByEmail.isPresent();

        // then
        assertThat(expected).isTrue();
    }

    @Test
    void itShouldNotFindStudentByEmail() {
        // given
        String email = "jamila@gmail.com";

        // when
        Optional<Student> studentByEmail = underTest.findStudentByEmail(email);
        boolean expected = studentByEmail.isPresent();

        // then
        assertThat(expected).isFalse();
    }
}