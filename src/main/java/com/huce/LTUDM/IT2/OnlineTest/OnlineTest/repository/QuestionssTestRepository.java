package com.huce.LTUDM.IT2.OnlineTest.OnlineTest.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.entity.QuestionssTest;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionssTestRepository extends CrudRepository<QuestionssTest, Long> {

    @Query("SELECT q FROM QuestionssTest q where q.test.id = :id")
    public List<QuestionssTest> getQuestionssTestByExamID(@Param("id") long id);
}
