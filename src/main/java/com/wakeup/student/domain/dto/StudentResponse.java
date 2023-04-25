package com.wakeup.student.domain.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Builder
@ToString
public class StudentResponse {
    private String stdSeq;
    private String stdNm;
    private String stdBthDt;
    private String stdSchool;
    private String regDt;
    private String stdGb;
    private String stdGender;
    private String prtCelNo;
}
