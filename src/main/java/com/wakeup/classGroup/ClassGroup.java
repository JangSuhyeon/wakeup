package com.wakeup.classGroup;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/class")
public class ClassGroup {

    @GetMapping("/")
    public String showClassList(){
        System.out.println("classList");
        return "/class/list";
    }

}
