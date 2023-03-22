package com.wakeup.student.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {

    @PostMapping("/regist")
    public ResponseEntity<String> regist(){
        return ResponseEntity.ok().body("등록이 완료되었습니다.");
    }
}
