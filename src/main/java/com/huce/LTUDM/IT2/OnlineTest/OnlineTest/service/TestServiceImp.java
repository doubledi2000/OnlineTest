package com.huce.LTUDM.IT2.OnlineTest.OnlineTest.service;

import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.common.Const;
import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.entity.*;
import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.repository.QuestionssTestRepository;
import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.repository.StudentssAnswerRepository;
import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.repository.TestRepository;
import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.sub.entity.SubTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.Collections;
@Service
public class TestServiceImp implements TestService, Const {

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
        for (Test t : tests) {
            t.setRealTime(new Date());
        }
        return tests;
    }

    @Override
    public Test getTestByTestID(long id) {
        Test test = repo.findById(id).get();
        test.setRealTime(new Date());
        return test;
    }

    @Override
    public List<Test> getTestByStudentIDandStatus(String id, String stt) {
        List<Test> tests = repo.getTestByStudentIDandStatus(id, stt);
        for (Test t : tests) {
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
            for (Answer ans : answers) {
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

    @Override
    public Test getOwnTestByExamCode(String id, String examCode) {
        return repo.getTestByStudentIDAndExamID(id, examCode);
    }

    @Override
    public List<SubTest> getOwnTestByStatus(String id, String status) {
        List<SubTest> tests = new ArrayList<>();
        List<Test> mainTest = repo.getTestByStudentIDandStatus(id, status);
        if (status.equals(TEST_STT_WAITING)) {
            List<Test> subTest = repo.getTestByStudentIDandStatus(id, TEST_STT_GOING_ON);
            mainTest.addAll(0, subTest);
            Collections.sort(mainTest);
        }
        for (Test t : mainTest) {
            switch (t.getStatus()) {
                case TEST_STT_WAITING:
                case TEST_STT_GOING_ON:
                    SubTest s = new SubTest();
                    s.setId(t.getId());
                    s.setProfessor(t.getProfessor());
                    s.setTime(t.getTime());
                    s.setStartTest(new Date(t.getExam().getStartTime().getTime() + 7 * 60 * 60 * 1000));
                    s.setRealTime(new Date(System.currentTimeMillis() + 7 * 60 * 60 * 1000));
                    s.setStatus(t.getStatus());
                    s.setExamCode(t.getExam().getExamCode());
                    s.setTitle(t.getTitle());
                    tests.add(s);
                    break;
                case TEST_STT_TOOK_PLACE:
                    s = new SubTest();
                    s.setId(t.getId());
                    s.setProfessor(t.getProfessor());
                    s.setTime(t.getTime());
                    s.setNoq(t.getNoq());
                    s.setCorrect_answers(t.getCorrectAnswer());
                    s.setScore(t.getScore());
                    s.setSubmissionTime(t.getSubmissionTime());
                    s.setExamCode(t.getExam().getExamCode());
                    s.setTitle(t.getTitle());
                    s.setStatus(t.getStatus());
                    tests.add(s);
                    break;

                case TEST_STT_PENDING:
                    break;

                default:
                    tests = null;
                    break;
            }
        }
        return tests;
    }


}
