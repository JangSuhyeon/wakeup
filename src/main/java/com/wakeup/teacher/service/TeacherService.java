package com.wakeup.teacher.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wakeup.teacher.domain.BoardVO;
import com.wakeup.teacher.domain.SearchBoardDTO;
import com.wakeup.teacher.mapper.TeacherMapper;

@Service
public class TeacherService {
	
	@Autowired
	private TeacherMapper teacherMapper;
	
	public HashMap<String, String> countMemberToday() {
		return teacherMapper.countMemberToday();
	}

	public HashMap<String, String> countLectureToday() {
		return teacherMapper.countLectureToday();
	}

	public int selectTotalAmtToday() {
		return teacherMapper.selectTotalAmtToday();
	}

	public List<BoardVO> selectBoard(SearchBoardDTO searchBoard) {
		return teacherMapper.selectBoard(searchBoard);
	}

}
