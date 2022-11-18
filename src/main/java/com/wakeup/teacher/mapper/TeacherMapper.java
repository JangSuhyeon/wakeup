package com.wakeup.teacher.mapper;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TeacherMapper {
	
	HashMap<String, String> countMemberToday();

	HashMap<String, String> countLectureToday();

	int selectTotalAmtToday();

}
