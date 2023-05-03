package com.example.demo.student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.time.LocalDate;
import java.time.Month;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class )
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;
    // private AutoCloseable autoCloseable;
    private StudentService underTest;

   /* @BeforeEach
    void setUp() {
        // autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new StudentService(studentRepository, massarCodeRepository);
    }

    /* @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    } */


    // -----------------------------------------------------------------

    @Test
    void canGetStudentsIsEmpty() {

        // given

        // when
        when(studentRepository.findAll()).thenReturn(Collections.emptyList());

        List<Student> students = underTest.getStudents();

        // then
        assertThat(students.size()).isEqualTo(0);

    }

    @Test
    void canGetStudents() {
        // when
        underTest.getStudents();
        // then
        verify(studentRepository).findAll();
    }

    // -----------------------------------------------------------------
/*
    @Test
    void canAddNewStudent() {
        // given
        Student student = new Student(
                "Zouhair",
                "zouhair@gmail.com",
                LocalDate.of(1996, Month.MARCH, 27)
        );

        // when
        underTest.addNewStudent(student);

        // then
        ArgumentCaptor<Student> studentArgumentCaptor =
                ArgumentCaptor.forClass(Student.class);

        verify(studentRepository).save(studentArgumentCaptor.capture());

        Student captureStudent = studentArgumentCaptor.getValue();

        assertThat(captureStudent).isEqualTo(student);

    }

    @Test
    void emailTaken() {
        // given
        Student student = new Student(
                "Zouhair",
                "zouhair@gmail.com",
                LocalDate.of(1996, Month.MARCH, 27)
        );
                given(studentRepository.findStudentByEmail(student.getEmail()))
                .willReturn(Optional.of(student));


        // when
        // then
        assertThatThrownBy( () -> underTest.addNewStudent(student))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("email taken");

         verify(studentRepository, never()).save(any());
    }
*/
    // -----------------------------------------------------------------


    @Test
    void canGetStudentById() {
        // given
        long id = 1;
        Student student = new Student(
                id,
                "Zouhair",
                "zouhair@gmail.com",
                LocalDate.of(1996, Month.MARCH, 27)
        );
        // when
        given(studentRepository.findById(id))
                .willReturn(Optional.of(student));

        Student studentById = underTest.getStudentById(id);

        // then
        assertThat(studentById).isEqualTo(student);


        verify(studentRepository).findById(id);
    }

    @Test
    void canGetStudentByIdIsNotExists() {
        // given
        long id = 1;
        // when
        //String message = "student with " + id + " does not exist";
        given(studentRepository.findById(id))
                .willThrow(new IllegalStateException("student with " + id + " does not exist"));
        // then
        assertThatThrownBy(() -> underTest.getStudentById(id))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("student with " + id + " does not exist");

    }

    // -------------------- Testing UpdateS ---------------------------


    @Test
    void updateStudent() {
        // given
        long id = 1;
        Student student = new Student(
                id,
                "Zouhair",
                "zouhair@gmail.com",
                LocalDate.of(1996, Month.MARCH, 27)
        );

        Student student1 = new Student(
                id,
                "Zou",
                "zou@gmail.com",
                LocalDate.of(1996, Month.MARCH, 27)
        );


        given(studentRepository.findById(id))
                .willReturn(Optional.of(student));

        // when
        underTest.updateStudent(student1, id);

        // then
        ArgumentCaptor<Student> studentArgumentCaptor =
                ArgumentCaptor.forClass(Student.class);

        verify(studentRepository).save(studentArgumentCaptor.capture());

        Student captureStudent = studentArgumentCaptor.getValue();

        assertThat(captureStudent).isEqualTo(student);
}


    // ------------------- Testing DeleteS -----------------------------

    @Test
    void canDeleteStudent() {
        // given
        long id = 10;
        given(studentRepository.existsById(id))
                .willReturn(true);

        // when
        String deleteId = underTest.deleteStudent(id);

        // then
        assertThat(deleteId).isEqualTo("Student with id "+id+" has been deleted success");

        verify(studentRepository).deleteById(id);

    }

    @Test
    void deleteStudentNotFound() {
        // given
        long id = 10;
        given(studentRepository.existsById(id))
                .willReturn(false);

        // when
        // then
        assertThatThrownBy(() -> underTest.deleteStudent(id))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("student with id " + id + " does not exists");

        verify(studentRepository, never()).deleteById(any());
    }
}
