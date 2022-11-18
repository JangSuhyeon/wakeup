package com.wakeup.teacher.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.wakeup.teacher.service.TeacherService;

@Controller
public class TeacherController {
	
	@Autowired
	private TeacherService teacherService;
	
	// 대시보드 화면
	@GetMapping("/teacher")
	public String goToDashboard(Model model) {
		HashMap<String, String> memberCnt = new HashMap<String, String>();
		HashMap<String, String> lectureCnt = new HashMap<String, String>();
		int totalAmt = 0;
		
		// 오늘 신규회원 count
		memberCnt = teacherService.countMemberToday();
		// 오늘 신규수강 count
		lectureCnt = teacherService.countLectureToday();
		// 오늘 총 매출
		totalAmt = teacherService.selectTotalAmtToday();
		
		model.addAttribute("memberCnt",memberCnt);
		model.addAttribute("lectureCnt",lectureCnt);
		model.addAttribute("totalAmt",totalAmt);
		
		return "teacher/dashboard";
	}

}
