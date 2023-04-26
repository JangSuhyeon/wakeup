package com.wakeup.student.service;

import com.wakeup.common.dto.Code;
import com.wakeup.student.domain.Student;
import com.wakeup.student.domain.dto.StudentResponse;
import com.wakeup.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public List<StudentResponse> getStudentList() {

        List<StudentResponse> studentResponseList = studentRepository.findStudentList().stream().map(tuple -> {
            Student student = tuple.get(0, Student.class);  // Student Table
            Code stdGbCode = tuple.get(1, Code.class);      // Code Table - stdGb
            Code stdGenderCode = tuple.get(2, Code.class);  // Code Table - stdGender
            return new StudentResponse(student, stdGbCode, stdGenderCode);
        }).collect(Collectors.toList());

        return studentResponseList;
    }
}
