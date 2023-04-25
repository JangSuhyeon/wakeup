package com.wakeup.common.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Code {

    @Column
    private String groupCode;

    @Column
    private String code;

    @Column
    private String codeName;

}
