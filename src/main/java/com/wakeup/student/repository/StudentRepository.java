package com.wakeup.student.repository;

import com.wakeup.student.domain.Student;
import org.hibernate.query.JpaTuple;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT s, c, c2 FROM Student s LEFT JOIN Code c ON s.stdGb = c.code AND c.groupCode = 'std_gb' LEFT JOIN Code c2 ON s.stdGender = c2.code AND c2.groupCode = 'gender'")
    Page<JpaTuple> findStudentList(Pageable pageable);

}
