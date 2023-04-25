package com.wakeup.student.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String stdSeq;

    @Column
    private String stdNm;

    @Column
    private String stdBthDt;

    @Column
    private String stdSchool;

    @Column
    private String regDt;

    @Column
    private String stdGb;

    @Column
    private String stdGender;

    @Column
    private String prtCelNo;

    @Column
    private String codeName;

}
