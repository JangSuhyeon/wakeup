package com.wakeup.teacher.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wakeup.teacher.domain.BoardVO;
import com.wakeup.teacher.domain.SearchBoardDTO;
import com.wakeup.teacher.domain.SearchStudentDTO;
import com.wakeup.teacher.domain.StudentVO;
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

	public List<BoardVO> selectBoardList(SearchBoardDTO searchBoard) {
		return teacherMapper.selectBoardList(searchBoard);
	}

	public List<StudentVO> selectDashStudentList(SearchStudentDTO searchStudent) {
		return teacherMapper.selectDashStudentList(searchStudent);
	}

}
