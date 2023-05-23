package com.wakeup.lecture.domain.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

public class LectureResponse {

    private Long lec_id;
    private Long cls_id;
    private String lec_sta_sn;
    private String pay_sta_sn;
    private int cls_amt;
    private int pay_amt;
    private int ref_amt;
    private Date reg_dt;
    private Date upd_dt;
    private long emp_id;

}
