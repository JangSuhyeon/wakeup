package com.wakeup.teacher.student.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudentController {
	
	@GetMapping("/student")
	public String goToStudentList() {
		
		return "teacher/student/list";
	}

}
