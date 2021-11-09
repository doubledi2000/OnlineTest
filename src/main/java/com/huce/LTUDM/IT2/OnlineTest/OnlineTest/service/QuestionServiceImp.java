package com.huce.LTUDM.IT2.OnlineTest.OnlineTest.service;

import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.entity.Question;
import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class QuestionServiceImp implements QuestionService {

    @Autowired
    QuestionRepository repo;

    @Override
    public void createQuestion(Question ques) {
        repo.save(ques);
    }

    @Override
    public void updateQuestion(long id, Question ques) {
        ques.setId(id);
        repo.save(ques);
    }

    @Override
    public void deleteQuestion(long id) {
        repo.deleteById(id);
    }

    @Override
    public Collection<Question> getQuestionByExamID(String id) {
        return null;
    }

}
