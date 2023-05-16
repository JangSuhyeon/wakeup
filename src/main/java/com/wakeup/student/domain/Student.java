package com.wakeup.student.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
