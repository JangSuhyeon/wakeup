package com.wakeup.classGroup.controller;

import com.wakeup.classGroup.domain.dto.ClassGroupResponse;
import com.wakeup.classGroup.service.ClassGroupService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/class")
public class ClassGroupController {

    private final ClassGroupService classGroupService;

    @GetMapping("")
    public String showClassList(Model model){

        // 학급 목록 조회
        List<ClassGroupResponse> classGroupList = classGroupService.findAll();
        model.addAttribute("classGroupList", classGroupList.get(0));

        return "/class/list";
    }

}
