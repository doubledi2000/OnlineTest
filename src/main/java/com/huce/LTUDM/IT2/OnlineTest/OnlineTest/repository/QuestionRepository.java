package com.huce.LTUDM.IT2.OnlineTest.OnlineTest.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.entity.Question;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface QuestionRepository extends CrudRepository<Question, Long> {

    @Query("SELECT q FROM Question q where q.exam.examCode = :id")
    public Collection<Question> getQuestionByExamID(@Param("id") String id);

}
