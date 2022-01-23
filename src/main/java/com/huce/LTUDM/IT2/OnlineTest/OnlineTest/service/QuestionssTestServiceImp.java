package com.huce.LTUDM.IT2.OnlineTest.OnlineTest.service;

import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.entity.Question;
import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.entity.QuestionssTest;
import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.repository.QuestionssTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class QuestionssTestServiceImp implements QuestionssTestService {

    @Autowired
    QuestionssTestRepository repo;

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
}
