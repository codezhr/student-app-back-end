package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;


/*@Configuration*/
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
           Student mariam = new Student(
                    "Mariam",
                    "mariam@gmail.com",
                    LocalDate.of(1998, Month.JANUARY,11)
            );
            Student anas = new Student(
                    "Anas",
                    "anas@gmail.com",
                    LocalDate.of(1996, Month.MARCH,11)
            );

            repository.saveAll(
                    List.of(mariam, anas)
            );
        };
    }
}
