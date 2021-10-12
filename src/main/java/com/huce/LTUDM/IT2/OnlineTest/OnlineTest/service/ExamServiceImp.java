package com.huce.LTUDM.IT2.OnlineTest.OnlineTest.service;

import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.entity.Answer;
import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.entity.Exam;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ExamServiceImp implements ExamService {
    @Override
    public void createExam(Exam exam) {

    }

    @Override
    public void updateExam(String id, Exam exam) {

    }

    @Override
    public void deleteExam(String id) {

    }

    @Override
    public Collection<Answer> getAnswerByProfessorID(String id) {
        return null;
    }

    @Override
    public Collection<Exam> getAll() {
        return null;
    }
}
