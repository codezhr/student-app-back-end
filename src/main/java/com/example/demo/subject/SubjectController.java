package com.example.demo.subject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

    private final SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @PostMapping
    public Subject creatSubject(@RequestBody Subject subject){
        return subjectService.creatNewSubject(subject);
    }

    @GetMapping
    public List<Subject> getSubjects(){
        return subjectService.getAllSubjects();
    }

    @PutMapping("{subjectId}/student/{studentId}")
    public Subject enrollStudentToSubject(
            @PathVariable Long subjectId,
            @PathVariable Long studentId
    ){
        return subjectService.enrollNewStudentToSubject(subjectId, studentId);
    }

    @PutMapping("{subjectId}/teacher/{teacherId}")
    public Subject enrollTeacherToSubject(
            @PathVariable Long subjectId,
            @PathVariable Long teacherId
    ){
        return subjectService.enrollNewTeacherToSubject(subjectId, teacherId);
    }


}
