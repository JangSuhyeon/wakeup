package com.wakeup.student.controller;

import com.wakeup.student.domain.Student;
import com.wakeup.student.domain.dto.StudentResponse;
import com.wakeup.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    @GetMapping("")
    public String list() {
        return "/student/list";
    }

    @PostMapping("")
    public String listJson(Model model) {
        // 학생 목록 데이터를 가져오는 로직
        List<StudentResponse> studentList = studentService.getStudentList();
        System.out.println("studentList : " + studentList);

        // 학생 목록 데이터를 모델에 담아서 Thymeleaf 템플릿에 전달
        model.addAttribute("studentList", studentList);

        // 학생 목록 데이터를 포함한 Thymeleaf 템플릿 파일의 경로를 반환
        return "/student/list :: #studentList";
    }

    @PostMapping("/regist")
    public ResponseEntity<String> regist(Authentication authentication) {

        return ResponseEntity.ok().body(authentication.getName() + "님의 등록이 완료되었습니다.");
    }
}
