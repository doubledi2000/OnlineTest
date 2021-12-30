package com.huce.LTUDM.IT2.OnlineTest.OnlineTest.repository;

import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.common.Const;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.entity.Test;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TestRepository extends CrudRepository<Test, Long>, Const {

    @Query("SELECT t FROM Test t where t.exam.examCode = :id order by t.startTime")
    public List<Test> getTestInExam(@Param("id") String id);

    @Query("SELECT t FROM Test t WHERE t.student.studentCode = :id order by t.startTime")
    public List<Test> getTestByStudentID(@Param("id") String id);

    @Query("SELECT t FROM Test t where t.student.studentCode = :id and t.status = :status order by t.startTime")
    public List<Test> getTestByStudentIDandStatus(@Param("id") String id, @Param("status") int status );


}
