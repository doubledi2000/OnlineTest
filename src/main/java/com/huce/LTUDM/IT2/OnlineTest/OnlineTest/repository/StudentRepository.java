package com.huce.LTUDM.IT2.OnlineTest.OnlineTest.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.entity.Student;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends CrudRepository<Student, String> {

    @Query("SELECT com.huce.LTUDM.IT2.ONlineTest.OnlineTest.entity.Student(e.tests.student) FROM Exam e where e.examCode = :examCode")
    public List<Student> getStudentsInExam(@Param("examCode") String id);
}
