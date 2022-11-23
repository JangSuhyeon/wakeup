package com.wakeup.teacher.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class StudentVO {
	
	// tb_student
	private int no;           //순번
	private String stdNm;     //학생명
	private String schClsSn;  //학력
	private String schNm;     //학교명
	private String schAcaSn;  //학년
	private String regDt;     //등록일자
	
	// tb_class_organize
	private String classOgnNm;   //학급명
	private String classOgnYm;   //편성년월

}
