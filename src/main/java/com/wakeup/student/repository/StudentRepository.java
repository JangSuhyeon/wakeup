package com.wakeup.student.repository;

import com.wakeup.student.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT s FROM Student s LEFT JOIN Code c ON s.std_gb = c.code AND c.group_Code = 'std_gb'")
    List<Student> findAll();

}
