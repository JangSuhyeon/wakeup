package com.wakeup.student.domain.dto;

import com.wakeup.common.dto.Code;
import com.wakeup.student.domain.Student;
import jakarta.persistence.Column;
import lombok.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@AllArgsConstructor
@Builder
@Data
public class StudentResponse {
    private Long stdId;
    private String stdNm;
    private String stdBthDt;
    private String stdSchool;
    private String regDt;
    private String stdGb;
    private String stdGender;
    private String prtCelNo;


    public StudentResponse(Student student, Code stdGbCode, Code stdGenderCode) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        this.stdId = student.getStdId();
        this.stdNm = student.getStdNm();
        this.stdBthDt = student.getStdBthDt();
        this.stdSchool = student.getStdSchool();
        this.regDt = dateFormat.format(student.getRegDt());
        this.stdGb = student.getStdGb();
        this.stdGender = student.getStdGender();
        this.prtCelNo = student.getPrtCelNo();
        this.stdGb = stdGbCode.getCodeName();
        this.stdGender = stdGenderCode.getCodeName();
    }
}
