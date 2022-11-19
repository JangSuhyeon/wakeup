package com.wakeup.teacher.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class BoardVO {
	private int boardCd;
	private int boardTnfoCd;
	private String title;
	private String content;
	private String empCd;
	private Date regDt;
}
