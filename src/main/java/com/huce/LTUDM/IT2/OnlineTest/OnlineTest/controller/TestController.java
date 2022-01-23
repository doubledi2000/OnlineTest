package com.huce.LTUDM.IT2.OnlineTest.OnlineTest.controller;

import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.common.Const;
import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.entity.*;
import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.*;

@RestController
@RequestMapping("/student-test")
public class TestController implements Const {
    @Autowired
    TestService testService;
    @Autowired
    StudentssAnswerService studentssAnswerService;
    @Autowired
    ExamService examService;
    @Autowired
    StudentService studentService;
    @Autowired
    QuestionssTestService questionssTestService;
    @Autowired
    QuestionService questionService;

    //get Test
    @GetMapping("/{id}")
    public Test getTest(@PathVariable long id){
        return testService.getTestByTestID(id);
    }

    //submit Test
    @PutMapping("submit/{id}")
    public ResponseEntity<?> submitTest(@PathVariable long id, @RequestBody Collection<StudentssAnswer> answers){
        studentssAnswerService.summitTest(1l, answers);
        return new ResponseEntity<>(testService.getTestByTestID(1l), HttpStatus.OK);
    }

    //get test by studentID and status of test
    @GetMapping("/{id}/{status}")
    public List<Test> getTestsByStatus(@PathVariable("id") String studentID, @PathVariable("status") String status){
        List<Test> tests = testService.getTestByStudentIDandStatus(studentID,status);
        for (Test t : tests) {
            t.setRealTime(new Date());
        }
        return tests;
//        return (List<Test>) new ResponseEntity<List<Test>>(testService.getTestByStudentIDandStatus(studentID, status),HttpStatus.OK);
    }

    //join test
    @PostMapping("/join/{id}")
    public void joinTest(@RequestHeader MultiValueMap<String, String> headers, @PathVariable("id") String id){
        headers.getFirst("username");
        Exam exam = examService.getExamByID(id);
        Test test = new Test();
        if(exam.getStatus() == EXAM_STT_APPROVED) {
            test.setTitle(exam.getTitle());
            test.setStatus(TEST_STT_WAITING);
            test.setStartTime(exam.getStartTime());
            test.setRealTime(new Date());
            test.setSubmittionTime(new Date());
            test.setNoq(exam.getQuestionList().size());
            test.setCorrectAnswer(0);
            test.setScore(0);
            test.setStudent(studentService.getStudentById("030120"));
            test.setExam(exam);
            test.setTime(90);
            testService.createTest(test);
            testService.generateQuestion(test.getId());
        }

    }
    @GetMapping("take-a-test/{id}")
    public ResponseEntity<?> takeATest(@PathVariable("id") long id) {
        List<QuestionssTest> ques = questionssTestService.getQuestionByTestID(1);
        return new ResponseEntity<>(ques,HttpStatus.OK);
    }
}
