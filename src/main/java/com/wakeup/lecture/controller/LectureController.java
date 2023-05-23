package com.wakeup.lecture.controller;

import com.wakeup.lecture.domain.dto.LectureResponse;
import com.wakeup.lecture.service.LectureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/lecture")
public class LectureController {

    private final LectureService lectureService;

    @GetMapping("")
    public String showLectureList(Model model){

        List<LectureResponse> lectureResponses = lectureService.findAll();

        return "/lecture/list";
    }

}
