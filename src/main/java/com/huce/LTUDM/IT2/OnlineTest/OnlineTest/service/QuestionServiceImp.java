package com.huce.LTUDM.IT2.OnlineTest.OnlineTest.service;

import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.entity.Question;
import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.entity.QuestionssTest;
import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.repository.ExamRepository;
import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class QuestionServiceImp implements QuestionService {

    @Autowired
    QuestionRepository repo;

    @Autowired
    ExamRepository examRepository;

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
    public List<Question> getQuestionByExamID(String id) {
        return examRepository.findById(id).get().getQuestion();
    }



}
