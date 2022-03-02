package com.huce.LTUDM.IT2.OnlineTest.OnlineTest.service;

import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.entity.Question;
import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.entity.QuestionssTest;
import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.entity.Test;
import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.repository.QuestionssTestRepository;
import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.repository.TestRepository;
import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.sub.entity.SubQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class QuestionssTestServiceImp implements QuestionssTestService {

    @Autowired
    QuestionssTestRepository repo;
    @Autowired
    TestService testService;

    @Override
    public void createQuestionssTest(QuestionssTest questionssTest) {
        repo.save(questionssTest);
    }

    @Override
    public void updateQuestionssTest(long id, QuestionssTest questionssTest) {
        questionssTest.setId(id);
        repo.save(questionssTest);
    }

    @Override
    public void deleteQuestionssTest(long id) {
        repo.deleteById(id);
    }

    @Override
    public List<QuestionssTest> getQuestionByTestID(long id) {
        return repo.getQuestionssTestByExamID(id);
    }

    @Override
    public QuestionssTest getQuestionssTestByQuestionID(long id) {
        return null;
    }

    @Override
    public List<SubQuestion> getSubQuestionTestByTestID(long id) {
        Test test = testService.getTestByTestID(id);
        if (test == null) {
            return null;
        }
        List<QuestionssTest> questionssTests = test.getQuestionss();
        List<SubQuestion> questions = new ArrayList<>();
        for (QuestionssTest q: questionssTests) {
            SubQuestion sub = new SubQuestion(q);
            questions.add(sub);
        }
        return questions;
    }


}
