package com.wakeup.classGroup.service;

import com.wakeup.classGroup.domain.ClassGroup;
import com.wakeup.classGroup.domain.dto.ClassGroupResponse;
import com.wakeup.classGroup.repository.ClassGroupRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class ClassGroupService {

    private final ClassGroupRepository classGroupRepository;
    public List<ClassGroupResponse> findAll() {

        List<ClassGroupResponse> classGroupResponses = new ArrayList<>();

        List<ClassGroup> classGroupList = classGroupRepository.findAll();

        for(ClassGroup classGroup : classGroupList) {
            Long clsId = classGroup.getClsId();
            String clsNm = classGroup.getClsNm();
            String clsYm = classGroup.getClsYm();
            Long empId = classGroup.getEmpId();
            ClassGroupResponse classGroupResponse = new ClassGroupResponse(clsId, clsNm, clsYm, empId);

            classGroupResponses.add(classGroupResponse);
        }

        return classGroupResponses;
    }
}
