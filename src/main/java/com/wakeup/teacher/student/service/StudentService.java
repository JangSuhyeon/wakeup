package com.wakeup.teacher.student.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wakeup.teacher.domain.SearchStudentDTO;
import com.wakeup.teacher.domain.StudentVO;
import com.wakeup.teacher.student.mapper.StudentMapper;

@Service
public class StudentService {
	
	@Autowired
	private StudentMapper studentMapper;

	public List<StudentVO> selectDashStudentList(SearchStudentDTO searchStudent) {
		return studentMapper.selectDashStudentList(searchStudent);
	}

}
