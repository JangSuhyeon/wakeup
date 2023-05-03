package com.wakeup.student.service;

import com.wakeup.common.dto.Code;
import com.wakeup.student.domain.Student;
import com.wakeup.student.domain.dto.StudentResponse;
import com.wakeup.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.JpaTuple;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public Page<StudentResponse> getStudentList(Pageable pageable) {

        Page<JpaTuple> studentTuples = studentRepository.findStudentList(pageable);
        List<StudentResponse> studentList = new ArrayList<>();

        for (JpaTuple tuple : studentTuples) {
            Student student = tuple.get(0, Student.class);
            Code stdGb = tuple.get(1, Code.class);
            Code stdGender = tuple.get(2, Code.class);

            StudentResponse studentResponse = new StudentResponse(student, stdGb, stdGender);
            studentList.add(studentResponse);
        }

        return new PageImpl<>(studentList, pageable, studentTuples.getTotalElements());
    }

    public StudentResponse findByStdCd(String stdId) {

        JpaTuple tuple = studentRepository.findByStdCd(stdId);
        Student student = tuple.get(0, Student.class);
        Code stdGb = tuple.get(1, Code.class);
        Code stdGender = tuple.get(2, Code.class);

        return new StudentResponse(student, stdGb, stdGender);
    }
}
