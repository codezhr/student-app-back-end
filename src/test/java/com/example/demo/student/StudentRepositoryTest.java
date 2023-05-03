package com.example.demo.student;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository underTest;

    @AfterEach
    void tearDown() {
         underTest.deleteAll();
    }

    @Test
    void checkToFindStudentsByEmail() {
        // given
        String email = "zouhair@gmail.com";
        Student student = new Student(
                "Zouhair",
                email,
                LocalDate.of(1996, Month.MARCH, 27)
        );
        underTest.save(student);

        // when
        Optional<Student> expected = underTest.findStudentByEmail(email);

        // then
        assertThat(expected).isPresent();
    }

    @Test
    void checkToCantFindStudentsByEmail() {
        // given
        String email = "zouhair@gmail.com";

        // when
        Optional<Student> expected = underTest.findStudentByEmail(email);

        // then
        assertThat(expected).isEmpty();
    }
}