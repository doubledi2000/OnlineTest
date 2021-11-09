package com.huce.LTUDM.IT2.OnlineTest.OnlineTest.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.entity.StudentssAnswer;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentssAnswerRepository extends CrudRepository<StudentssAnswer, Long> {

    @Query("SELECT s FROM StudentssAnswer s WHERE s.question.id = :id")
    public List<StudentssAnswer> getStudentssAnswerByQuestionID(@Param("id") long id);
}
