package com.example.demo.codeMassar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/massarcode")
public class MassarCodeController {

    private final MassarCodeService massarCodeService;


    @Autowired
    public MassarCodeController(MassarCodeService massarCodeService) {
        this.massarCodeService = massarCodeService;
    }



    @PostMapping()
    public MassarCode addCode(@RequestBody MassarCode massarCode){
        return massarCodeService.addNewCode(massarCode);
    }

    @GetMapping()
    public List<MassarCode> getCode(){
        return massarCodeService.getAllCode();
    }

    @PutMapping("/{massarId}/student/{studentId}")
    public MassarCode enrollStudentToMassar(
            @PathVariable Long massarId,
            @PathVariable Long studentId
    ){
        return massarCodeService.enrollNewStudentToMassar(massarId,studentId);
    }


}
