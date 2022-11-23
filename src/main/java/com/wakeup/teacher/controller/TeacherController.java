package com.wakeup.teacher.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.wakeup.teacher.domain.SearchBoardDTO;
import com.wakeup.teacher.domain.SearchStudentDTO;
import com.wakeup.teacher.domain.StudentVO;
import com.wakeup.teacher.domain.BoardVO;
import com.wakeup.teacher.service.TeacherService;
import com.wakeup.teacher.student.service.StudentService;

@Controller
public class TeacherController {
	
	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private StudentService studentService;
	
	// 대시보드 화면
	@GetMapping("/dashboard")
	public String goToDashboard(Model model) {
		HashMap<String, String> memberCnt = new HashMap<String, String>();
		HashMap<String, String> lectureCnt = new HashMap<String, String>();
		List<BoardVO> noticeList = new ArrayList<>();
		List<StudentVO> studentList = new ArrayList<>();
		List<BoardVO> newsList = new ArrayList<>();
		int totalAmt = 0;
		
		// 오늘 신규회원 count
		memberCnt = teacherService.countMemberToday();
		// 오늘 신규수강 count
		lectureCnt = teacherService.countLectureToday();
		// 오늘 총 매출
		totalAmt = teacherService.selectTotalAmtToday();
		
		// 공지사항
		SearchBoardDTO searchBoard1 = new SearchBoardDTO();
		searchBoard1.setBoardCd(1);
		searchBoard1.setLimit(6);
		noticeList = teacherService.selectBoardList(searchBoard1);
		
		// 신규회원
		SearchStudentDTO searchStudent = new SearchStudentDTO();
		searchStudent.setLimit(5);
		studentList = studentService.selectDashStudentList(searchStudent);
		
		// 학원소식
		SearchBoardDTO searchBoard2 = new SearchBoardDTO();
		searchBoard2.setBoardCd(2);
		searchBoard2.setLimit(3);
		newsList = teacherService.selectBoardList(searchBoard2);
		
		model.addAttribute("memberCnt",memberCnt);
		model.addAttribute("lectureCnt",lectureCnt);
		model.addAttribute("totalAmt",totalAmt);
		model.addAttribute("noticeList", noticeList);
		model.addAttribute("studentList", studentList);
		model.addAttribute("newsList", newsList);
		
		return "teacher/dashboard";
	}

}
