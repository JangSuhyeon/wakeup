package com.wakeup.teacher.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class StudentVO {
	
	private int no;           //순번
	private String stdNm;     //학생명
	private String classOgnNm;   //학급명
	private String classOgnYm;   //편성년월
	private String regDt;     //등록일자

}
