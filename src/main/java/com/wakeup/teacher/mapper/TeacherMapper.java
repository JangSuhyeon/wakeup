package com.wakeup.teacher.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.wakeup.teacher.domain.BoardVO;
import com.wakeup.teacher.domain.SearchBoardDTO;

@Mapper
public interface TeacherMapper {
	
	HashMap<String, String> countMemberToday();

	HashMap<String, String> countLectureToday();

	int selectTotalAmtToday();

	List<BoardVO> selectBoard(SearchBoardDTO boardCd);

}
