package com.huce.LTUDM.IT2.OnlineTest.OnlineTest.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.entity.Test;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TestRepository extends CrudRepository<Test, Long> {

    @Query("SELECT t FROM Test t where t.exam.examCode = :id")
    public List<Test> getTestInExam(@Param("id") String id);

    @Query("SELECT t FROM Test t WHERE t.student.studentCode = :id")
    public List<Test> getTestByStudentID(@Param("id") String id);
}
