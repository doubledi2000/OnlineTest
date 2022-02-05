package com.huce.LTUDM.IT2.OnlineTest.OnlineTest.service;

import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.entity.*;
import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.repository.QuestionssTestRepository;
import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.repository.StudentssAnswerRepository;
import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TestServiceImp implements TestService {

    @Autowired
    private TestRepository repo;
    @Autowired
    private QuestionssTestRepository questionssTestRepository;
    @Autowired
    private StudentssAnswerRepository studentssAnswerRepository;

    @Override
    public void createTest(Test test) {
        repo.save(test);
    }

    @Override
    public void updateTest(long id, Test test) {
        test.setId(id);
        repo.save(test);
    }

    @Override
    public void deleteTest(long id) {
        repo.deleteById(id);
    }

    @Override
    public Collection<Test> getTestByExamID(String id) {
        return repo.getTestInExam(id);
    }

    @Override
    public Collection<Test> getTestByStudentID(String id) {
        Collection<Test> tests = repo.getTestByStudentID(id);
        for(Test t : tests){
            t.setRealTime(new Date());
        }
        return tests;
    }

    @Override
    public Test getTestByTestID(long id) {
        Test test =repo.findById(id).get();
        test.setRealTime(new Date());
        return test;
    }

    @Override
    public List<Test> getTestByStudentIDandStatus(String id, String stt) {
        List<Test> tests = repo.getTestByStudentIDandStatus(id, stt);
        for(Test t : tests){
            t.setRealTime(new Date());
        }
        return tests;
    }

    @Override
    public void generateQuestion(long id) {
        List<QuestionssTest> questionssTests = new ArrayList<>();

        Exam exam = repo.findById(id).get().getExam();
        List<Question> questions = exam.getQuestion();
        Collections.shuffle(questions);
        for (Question q : questions) {
            List<Answer> answers = q.getAnswers();
            Collections.shuffle(answers);

            QuestionssTest qus = new QuestionssTest();
            qus.setQuestionId(q.getId());
            qus.setTitle(q.getTitle());
            qus.setImage(q.getImage());
            qus.setType(q.getType());
            qus.setTest(repo.findById(id).get());
            qus.setResult(0);

            List<StudentssAnswer> studentssAnswers = new ArrayList<>();
            questionssTestRepository.save(qus);
            for (Answer ans: answers) {
                StudentssAnswer studentssAnswer = new StudentssAnswer();
                studentssAnswer.setQuestion(qus);
                studentssAnswer.setChoose(false);
                studentssAnswer.setTrue_false(ans.getTF());
                studentssAnswer.setContent(ans.getContent());
                studentssAnswers.add(studentssAnswer);
                studentssAnswerRepository.save(studentssAnswer);
            }
            qus.setStudentssAnswers(studentssAnswers);
            questionssTests.add(qus);

        }
    }

    @Override
    public List<Test> getOwnTest(String id) {
        return repo.getTestByStudentID(id);
    }


}
