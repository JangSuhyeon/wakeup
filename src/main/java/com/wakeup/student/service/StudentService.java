package com.wakeup.student.service;

import com.wakeup.student.domain.Student;
import com.wakeup.student.domain.dto.StudentResponse;
import com.wakeup.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public List<StudentResponse> getStudentList() {

        List<Student> studentList = studentRepository.findAll(PageRequest.of(0, 20)).getContent();;

        return studentList.stream()
                .map(student -> StudentResponse.builder()
                        .stdSeq(student.getStdSeq())
                        .stdNm(student.getStdNm())
                        .stdBthDt(student.getStdBthDt())
                        .stdSchool(student.getStdNm())
                        .regDt(student.getRegDt())
                        .stdGb(student.getStdGb())
                        .stdGender(student.getStdGender())
                        .prtCelNo(student.getPrtCelNo()).build())
                .collect(Collectors.toList());
    }
}
