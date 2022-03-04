package com.huce.LTUDM.IT2.OnlineTest.OnlineTest.service;

import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.entity.Answer;
import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.entity.QuestionssTest;
import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.entity.Test;
import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.sub.entity.SubTest;

import java.util.Collection;
import java.util.List;

public interface TestService {
    public abstract void createTest(Test test);

    public abstract void updateTest(long id, Test test);

    public abstract void deleteTest(long id);

    public abstract Collection<Test> getTestByExamID(String id);

    public abstract Collection<Test> getTestByStudentID(String id);

    public abstract Test getTestByTestID(long id);

    public abstract List<Test> getTestByStudentIDandStatus(String id, String stt);

    public abstract void generateQuestion(long id);

    public abstract List<Test> getOwnTest(String id);

    public abstract List<SubTest> getOwnTestByStatus(String id, String status);

    public abstract SubTest getOwnTestByExamCode(String id, String examCode);

}
