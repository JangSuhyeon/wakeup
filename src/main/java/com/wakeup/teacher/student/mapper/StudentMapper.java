package com.wakeup.teacher.student.mapper;

import java.util.List;

import com.wakeup.teacher.domain.SearchStudentDTO;
import com.wakeup.teacher.domain.StudentVO;

public interface StudentMapper {

	List<StudentVO> selectDashStudentList(SearchStudentDTO searchStudent);

}
