package com.huce.LTUDM.IT2.OnlineTest.OnlineTest.service;

import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.entity.Answer;
import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.entity.QuestionssTest;
import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.sub.entity.SubQuestion;

import java.util.Collection;
import java.util.List;

public interface QuestionssTestService {
    public abstract void createQuestionssTest(QuestionssTest questionssTest);

    public abstract void updateQuestionssTest(long id, QuestionssTest questionssTest);

    public abstract void deleteQuestionssTest(long id);

    public abstract List<QuestionssTest> getQuestionByTestID(long id);

    public abstract QuestionssTest getQuestionssTestByQuestionID(long id);

    public abstract List<SubQuestion> getSubQuestionTestByTestID(long id);
}
