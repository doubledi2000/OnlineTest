package com.huce.LTUDM.IT2.OnlineTest.OnlineTest.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.entity.Answer;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface AnswerRepository extends CrudRepository<Answer, Long> {

    @Query("SELECT a FROM Answer a WHERE a.question.id = :id")
    public Collection<Answer> getAnswerByQuestionID(@Param("id") long id);

}
