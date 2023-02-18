package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/getstudents")
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @PostMapping("/poststudent")
    public Student registerStudent(@RequestBody Student student){
       return studentService.addNewStudent(student);
    }

    @GetMapping("/student/{id}")
    public Student getStudentById(@PathVariable Long id) { return studentService.getStudentById(id); }

    @PutMapping("/student/{id}")
    public Student updateStudent(@RequestBody Student newStudent,@PathVariable Long id){
        return studentService.updateStudent(newStudent, id);
    }

    @DeleteMapping("/student/{id}")
    String deleteStudent(@PathVariable Long id) {
       return studentService.deleteStudent(id);
    }



  /*  @PutMapping("/student/{id}")
    public void updateStudent(
           @PathVariable Long id,
           @RequestParam(required = false) String name,
           @RequestParam(required = false) String email) {
        studentService.updateStudent(id, name, email);
    } */

}
