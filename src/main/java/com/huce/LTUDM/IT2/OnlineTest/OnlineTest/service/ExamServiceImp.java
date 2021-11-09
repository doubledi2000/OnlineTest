package com.huce.LTUDM.IT2.OnlineTest.OnlineTest.service;

import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.entity.Answer;
import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.entity.Exam;
import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.repository.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ExamServiceImp implements ExamService {
    @Autowired
    ExamRepository repo;

    @Override
    public void createExam(Exam exam) {
        repo.save(exam);
    }

    @Override
    public void updateExam(String id, Exam exam) {
        exam.setExamCode(id);
        repo.save(exam);
    }

    @Override
    public void deleteExam(String id) {
        repo.deleteById(id);
    }

    @Override
    public Collection<Exam> getExamByProfessorID(String id) {
        return repo.getExamByProfessorID(id);
    }

    @Override
    public Collection<Exam> getAll() {
        return (Collection<Exam>) repo.findAll();
    }

    @Override
    public Exam getExamByID(String id) {
        return repo.findById(id).get();
    }
}
