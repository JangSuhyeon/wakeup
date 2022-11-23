package com.wakeup.teacher.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.wakeup.teacher.domain.BoardVO;
import com.wakeup.teacher.domain.SearchBoardDTO;
import com.wakeup.teacher.domain.SearchStudentDTO;
import com.wakeup.teacher.domain.StudentVO;

@Mapper
public interface TeacherMapper {
	
	HashMap<String, String> countMemberToday();

	HashMap<String, String> countLectureToday();

	int selectTotalAmtToday();

	List<BoardVO> selectBoardList(SearchBoardDTO boardCd);

	List<StudentVO> selectDashStudentList(SearchStudentDTO searchStudent);

}
