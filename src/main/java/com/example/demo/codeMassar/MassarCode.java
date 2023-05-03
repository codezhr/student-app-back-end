package com.example.demo.codeMassar;

import com.example.demo.student.Student;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import javax.print.DocFlavor;

@Entity
@Table
public class MassarCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    @OneToOne(mappedBy = "massarCode")
    @JsonIgnore
    private Student student;


    public MassarCode() {
    }

    public MassarCode(Long id, String code) {
        this.id = id;
        this.code = code;
    }

    public MassarCode(String code) {
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void enrollStToMs(Student student) {
        this.student = student;
    }
}
