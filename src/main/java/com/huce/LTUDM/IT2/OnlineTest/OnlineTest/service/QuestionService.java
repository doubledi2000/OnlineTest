package com.huce.LTUDM.IT2.OnlineTest.OnlineTest.service;

import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.entity.Answer;
import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.entity.Question;

import java.util.Collection;

public interface QuestionService {
    public abstract void createQuestion(Question ques);

    public abstract void updateQuestion(long id, Question ques);

    public abstract void deleteQuestion(long id);

    public abstract Collection<Question> getQuestionByExamID(String id);

}
