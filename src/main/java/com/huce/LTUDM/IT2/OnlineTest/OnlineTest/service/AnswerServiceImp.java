package com.huce.LTUDM.IT2.OnlineTest.OnlineTest.service;

import java.util.Collection;

import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.entity.Answer;

@Service
public class AnswerServiceImp implements AnswerService {

    @Autowired
    AnswerRepository repo;

    @Override
    public void createAnswer(Answer ans) {
        // TODO Auto-generated method stub
        repo.save(ans);
    }

    @Override
    public void updateAnswer(long id, Answer ans) {
        // TODO Auto-generated method stub
        ans.setId(id);
        repo.save(ans);
    }

    @Override
    public void deleteAnswer(long id) {
        // TODO Auto-generated method stub
        repo.deleteById(id);
    }

    @Override
    public Collection<Answer> getAnswerByQuestionID(long id) {
        // TODO Auto-generated method stub
        return repo.getAnswerByQuestionID(id);
    }

}
