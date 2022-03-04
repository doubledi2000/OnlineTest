package com.huce.LTUDM.IT2.OnlineTest.OnlineTest.service;


import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.common.Const;
import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.entity.QuestionssTest;
import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.entity.StudentssAnswer;
import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.entity.Test;
import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.repository.AnswerRepository;
import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.repository.QuestionssTestRepository;
import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.repository.StudentssAnswerRepository;
import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.repository.TestRepository;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
public class StudentssAnswerServiceImp implements StudentssAnswerService, Const {

    @Autowired
    private StudentssAnswerRepository repo;
    @Autowired
    QuestionssTestRepository questionssTestRepository;
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private TestRepository testRepository;

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

    @Override
    public Test summitTest(long id, Collection<StudentssAnswer> answers) {
        for (StudentssAnswer answer : answers) {
            StudentssAnswer s = repo.findById(answer.getId()).get();
            if (s.getQuestion().getTest().getId() != id){
                continue;
            }
            s.setChoose(answer.getChoose());
            repo.save(s);
        }
        Test test = testRepository.findById(id).get();
        test.setSubmittionTime(new Date(System.currentTimeMillis() + 7 * 60 * 60 * 1000));
        List<QuestionssTest> questionssTests = test.getQuestionss();
        int correctAnswer = 0;
        for (QuestionssTest qus : questionssTests) {
            if (qus.getStudentssAnswers().size() == 0) continue;
            int i = 0;
            for (StudentssAnswer ans : qus.getStudentssAnswers()) {
                if (ans.getChoose() != ans.isTrue_false()) {
                    break;
                }
                i++;
                if (qus.getStudentssAnswers().size() == i) {
                    correctAnswer++;
                }
            }
        }
        test.setCorrectAnswer(correctAnswer);
        double sc = (double) correctAnswer / test.getNoq();
        test.setScore(Math.round(sc * 1000.0) / 100.0);
        test.setStatus(TEST_STT_TOOK_PLACE);
        testRepository.save(test);
        return test;
    }
}
