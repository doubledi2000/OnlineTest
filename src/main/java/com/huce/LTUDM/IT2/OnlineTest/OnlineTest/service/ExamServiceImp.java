package com.huce.LTUDM.IT2.OnlineTest.OnlineTest.service;

import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.common.Const;
import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.entity.Answer;
import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.entity.Exam;
import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.entity.Test;
import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.repository.ExamRepository;
import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.repository.TestRepository;
import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.sub.entity.SubExam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ExamServiceImp implements ExamService, Const {
    @Autowired
    ExamRepository repo;
    @Autowired
    TestRepository testRepository;

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

    @Override
    public Object getExamByStudentCodeAndExamID(String sc, String ex) {
        return repo.getExamByIDAndStudentCode(sc, ex);
    }

    @Override
    public SubExam getExamWithStatus(String studentCode, String ec) {
        Exam exam = repo.findById(ec).get();
        if(exam == null) {
            return new SubExam();
        }
        Test test = testRepository.getTestByStudentIDAndExamID(studentCode, ec);
        SubExam subExam = new SubExam();
        subExam.setExamCode(exam.getExamCode());
        subExam.setTitle(exam.getTitle());
        subExam.setProfessor(exam.getProfessor().getFullname());
        if(test == null) {
            subExam.setStatus(EXAM_JOIN_STT_NOT_YET);
        }else {
            if (test.getStudent().equals(TEST_STT_PENDING)){
                subExam.setStatus(EXAM_JOIN_STT_PENDING);
            }else {
                subExam.setStatus(EXAM_JOIN_STT_JOINED);
            }
        }

        return subExam;
    }

}
