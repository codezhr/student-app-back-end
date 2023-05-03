package com.example.demo.student;

import com.example.demo.codeMassar.MassarCode;
import com.example.demo.codeMassar.MassarCodeRepository;
import com.example.demo.codeMassar.MassarCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    private final MassarCodeService massarCodeService;

    @Autowired
    public StudentService(StudentRepository studentRepository, MassarCodeService massarCodeService) {
        this.studentRepository = studentRepository;
        this.massarCodeService = massarCodeService;
    }


    // GetStudents
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    // AddStudent
    public Student addNewStudent(Student student) {
       Optional<Student> studentOptional =
               studentRepository.findStudentByEmail(student.getEmail());
       if (studentOptional.isPresent()) {
           throw new IllegalStateException("email taken");
       }
    massarCodeService.addNewCode()
      return studentRepository.save(student);
    }


    // GetStudentById
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("student with " + id + " does not exist"));
    }

    // Update Student
    public Student updateStudent(Student newStudent, Long id) {
        Student student = getStudentById(id);
        if (student != null){
            student.setName(newStudent.getName());
            student.setEmail(newStudent.getEmail());
            student.setDob(newStudent.getDob());
            student.setAge(newStudent.getAge());
            return studentRepository.save(student);
        }
        return null;
    }

    // Delete Student
    public String deleteStudent(Long id) {
        boolean exists = studentRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("student with id " + id + " does not exists");
        }
        studentRepository.deleteById(id);
        return "Student with id "+id+" has been deleted success";
    }






    /*
    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("student with id " + studentId + " does not exist"));

        if (name != null && name.length() > 0 && !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }
        if (email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)){
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if (studentOptional.isPresent()) {
                throw new IllegalStateException("email taken");
            }
            student.setEmail(email);
        }

    }*/

}
