package com.example.demo.codeMassar;

import com.example.demo.student.Student;
import com.example.demo.student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MassarCodeService {

    private final MassarCodeRepository massarCodeRepository;

    private final StudentRepository studentRepository;

    @Autowired
    public MassarCodeService(MassarCodeRepository massarCodeRepository, StudentRepository studentRepository) {
        this.massarCodeRepository = massarCodeRepository;
        this.studentRepository = studentRepository;
    }




    public MassarCode addNewCode(MassarCode massarCode) {
        return massarCodeRepository.save(massarCode);
    }

    public List<MassarCode> getAllCode() {
        return massarCodeRepository.findAll();
    }


    public MassarCode enrollNewStudentToMassar(Long massarId, Long studentId) {
        MassarCode massarCode = massarCodeRepository.findById(massarId).get();
        Student student = studentRepository.findById(studentId).get();
        massarCode.enrollStToMs(student);
        return massarCodeRepository.save(massarCode);
    }
}


