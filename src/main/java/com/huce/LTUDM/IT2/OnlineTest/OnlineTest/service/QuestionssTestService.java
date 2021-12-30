package com.huce.LTUDM.IT2.OnlineTest.OnlineTest.service;

import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.entity.Answer;
import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.entity.QuestionssTest;

import java.util.Collection;

public interface QuestionssTestService {
    public abstract void createQuestionssTest(QuestionssTest questionssTest);

    public abstract void updateQuestionssTest(long id, QuestionssTest questionssTest);

    public abstract void deleteQuestionssTest(long id);

    public abstract Collection<QuestionssTest> getQuestionByTestID(long id);

    public abstract QuestionssTest getQuestionssTestByQuestionID(long id);

}
