package com.wakeup.student.controller;

import com.wakeup.student.domain.Student;
import com.wakeup.student.domain.dto.StudentDto;
import com.wakeup.student.service.StudentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    @GetMapping("")
    public String studentList(Model model, @PageableDefault(page = 1, size = 15, sort = "regDt", direction = Sort.Direction.DESC) Pageable pageable) {

        // 학생 목록을 가져오는 로직
        Page<StudentDto> studentList = studentService.getStudentList(pageable);

        model.addAttribute("studentList", studentList);

        return "/student/list";
    }

    @GetMapping("/{stdId}")
    public String studentDetail(@PathVariable("stdId") String stdId, Model model) {

        // 학생 상세 정보를 가져오는 로직
        StudentDto student = studentService.findByStdCd(stdId);

        model.addAttribute("student", student);

        return "/student/detail";
    }

    @ResponseBody
    @PostMapping("")
    public HashMap<String, String> studentSave(@RequestBody StudentDto newStudent){

        HashMap<String, String> response = new HashMap<>();

        StudentDto student = studentService.studentSave(newStudent);
        log.info("{} 학생 등록 완료", student.getStdNm());

        response.put("data", "success");

        return response;
    }

    @PostMapping("/regist")
    public ResponseEntity<String> regist(Authentication authentication) {

        return ResponseEntity.ok().body(authentication.getName() + "님의 등록이 완료되었습니다.");
    }
}
