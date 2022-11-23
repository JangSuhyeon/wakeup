package com.wakeup.teacher.student.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.wakeup.teacher.domain.SearchStudentDTO;
import com.wakeup.teacher.domain.StudentVO;
import com.wakeup.teacher.service.TeacherService;
import com.wakeup.teacher.student.service.StudentService;

@Controller
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@GetMapping("/student")
	public String goToStudentList() {
		
		// 학생목록
		SearchStudentDTO searchStudent = new SearchStudentDTO();
		searchStudent.setLimit(5);
		List<StudentVO> studentList = studentService.selectDashStudentList(searchStudent);
		
		return "teacher/student/list";
	}

}
