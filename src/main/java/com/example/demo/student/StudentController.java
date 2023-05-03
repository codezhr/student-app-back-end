package com.example.demo.student;

import com.example.demo.codeMassar.MassarCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("students")
/*@CrossOrigin("*")*/
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/")
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @PostMapping("/")
    public Student registerStudent(@RequestBody Student student){
       return studentService.addNewStudent(student);
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id); }

    @PutMapping("/{id}")
    public Student updateStudent(@RequestBody Student newStudent,@PathVariable Long id){
        return studentService.updateStudent(newStudent, id);
    }

    @DeleteMapping("/{id}")
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
