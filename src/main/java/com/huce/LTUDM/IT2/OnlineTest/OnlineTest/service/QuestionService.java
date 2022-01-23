package com.huce.LTUDM.IT2.OnlineTest.OnlineTest.service;

import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.entity.Answer;
import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.entity.Question;
import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.entity.QuestionssTest;

import java.util.Collection;
import java.util.List;

public interface QuestionService {
    public abstract void createQuestion(Question ques);

    public abstract void updateQuestion(long id, Question ques);

    public abstract void deleteQuestion(long id);

    public abstract List<Question> getQuestionByExamID(String id);

}
