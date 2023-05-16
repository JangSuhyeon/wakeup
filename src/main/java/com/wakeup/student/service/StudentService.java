package com.wakeup.student.service;

import com.wakeup.common.dto.Code;
import com.wakeup.exception.AppException;
import com.wakeup.exception.ErrorCode;
import com.wakeup.student.domain.Student;
import com.wakeup.student.domain.dto.StudentDto;
import com.wakeup.student.domain.dto.StudentDto;
import com.wakeup.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.JpaTuple;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public Page<StudentDto> getStudentList(Pageable pageable) {

        Page<JpaTuple> studentTuples = studentRepository.findStudentList(pageable);
        List<StudentDto> studentList = new ArrayList<>();

        for (JpaTuple tuple : studentTuples) {
            Student student = tuple.get(0, Student.class);
            Code stdGb = tuple.get(1, Code.class);
            Code stdGender = tuple.get(2, Code.class);

            StudentDto StudentDto = new StudentDto(student, stdGb, stdGender);
            studentList.add(StudentDto);
        }

        return new PageImpl<>(studentList, pageable, studentTuples.getTotalElements());
    }

    public StudentDto findByStdCd(String stdId) {

        JpaTuple tuple = studentRepository.findByStdCd(stdId);
        Student student = tuple.get(0, Student.class);
        Code stdGb = tuple.get(1, Code.class);
        Code stdGender = tuple.get(2, Code.class);

        return new StudentDto(student, stdGb, stdGender);
    }

    public StudentDto studentSave(StudentDto newStudent) {

        // 학생 중복 확인
        studentRepository.findByStdNmAndPrtCelNo(newStudent.getStdNm(), newStudent.getPrtCelNo())
                .ifPresent(student -> {
                    throw new AppException(ErrorCode.STUDENT_DUPLICATED,"이미 등록된 학생입니다.");
                });

        // 저장
        Student student = Student.builder()
                .stdNm(newStudent.getStdNm())
                .stdBthDt(newStudent.getStdBthDt())
                .stdSchool(newStudent.getStdSchool())
                .stdGender(newStudent.getStdGender())
                .prtCelNo(newStudent.getPrtCelNo())
                .regDt(new Date())
                .build();
        studentRepository.save(student);

        return StudentDto.builder()
                .stdNm(newStudent.getStdNm())
                .build();
    }
}
