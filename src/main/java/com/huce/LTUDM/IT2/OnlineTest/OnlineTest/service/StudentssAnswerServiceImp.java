package com.huce.LTUDM.IT2.OnlineTest.OnlineTest.service;


import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.entity.StudentssAnswer;
import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.repository.StudentssAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class StudentssAnswerServiceImp implements StudentssAnswerService {

    @Autowired
    private StudentssAnswerRepository repo;

    @Override
    public void createStudentssAnswer(StudentssAnswer answer) {
        repo.save(answer);
    }

    @Override
    public void updateStudentssAnswer(long id, StudentssAnswer answer) {
        answer.setId(id);
        repo.save(answer);
    }

    @Override
    public void deleteStudentssAnswer(long id) {
        repo.deleteById(id);
    }

    @Override
    public Collection<StudentssAnswer> getStudentssAnswerByQuestionID(long id) {
        return repo.getStudentssAnswerByQuestionID(id);
    }
}
