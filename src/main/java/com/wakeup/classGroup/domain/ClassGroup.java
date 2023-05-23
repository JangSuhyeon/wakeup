package com.wakeup.classGroup.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class ClassGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clsId;

    @Column
    private String clsNm;

    @Column
    private String clsYm;

    @Column
    private Long empId;

}
