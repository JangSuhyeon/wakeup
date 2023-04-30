package com.wakeup.student.controller;

import com.wakeup.student.domain.Student;
import com.wakeup.student.domain.dto.StudentResponse;
import com.wakeup.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    @GetMapping("")
    public String listJson(Model model, @PageableDefault(page = 1, size = 15, sort = "regDt", direction = Sort.Direction.DESC) Pageable pageable) {

        // 학생 목록 데이터를 가져오는 로직
        Page<StudentResponse> studentList = studentService.getStudentList(pageable);

        // 학생 목록 데이터를 모델에 담아서 Thymeleaf 템플릿에 전달
        model.addAttribute("studentList", studentList);

        // 학생 목록 데이터를 포함한 Thymeleaf 템플릿 파일의 경로를 반환
        return "/student/list";
    }

    @PostMapping("/regist")
    public ResponseEntity<String> regist(Authentication authentication) {

        return ResponseEntity.ok().body(authentication.getName() + "님의 등록이 완료되었습니다.");
    }
}
