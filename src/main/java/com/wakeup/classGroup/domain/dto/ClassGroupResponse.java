package com.wakeup.classGroup.domain.dto;

import com.wakeup.classGroup.domain.ClassGroup;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ClassGroupResponse {

    public Long clsId;
    public String clsNm;
    public String clsYm;
    public long empId;

}
