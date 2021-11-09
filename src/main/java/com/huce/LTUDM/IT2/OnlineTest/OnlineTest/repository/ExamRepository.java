package com.huce.LTUDM.IT2.OnlineTest.OnlineTest.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.entity.Exam;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface ExamRepository extends CrudRepository<Exam, String> {

    @Query("SELECT e from Exam e where e.professor.professorCode = :id")
    public Collection<Exam> getExamByProfessorID(@Param("id") String id);
}
