package com.wakeup.student.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stdId;

    @Column
    private String stdNm;

    @Column
    private String stdBthDt;

    @Column
    private String stdSchool;

    @Column
    private Date regDt;

    @Column
    private String stdGb;

    @Column
    private String stdGender;

    @Column
    private String prtCelNo;

}
