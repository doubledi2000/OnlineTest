package com.huce.LTUDM.IT2.OnlineTest.OnlineTest.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.entity.Exam;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface ExamRepository extends CrudRepository<Exam, String> {

    @Query("SELECT e from Exam e where e.professor.professorCode = :id")
    public Collection<Exam> getExamByProfessorID(@Param("id") String id);

    @Query(value = "SELECT exam_code, status FROM test where student_code = :sc and exam_code = :ex", nativeQuery = true)
    public Object getExamByIDAndStudentCode(@Param("sc") String studentCode, @Param("ex") String examCode);

    @Query(value = "SELECT exam_code, status FROM test limit 1", nativeQuery = true)
    public Object getTEST();
}

