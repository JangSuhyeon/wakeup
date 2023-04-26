package com.wakeup.common.dto;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Code {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codeId;

    @Column
    private String groupCode;

    @Column
    private String code;

    @Column
    private String codeName;

}
