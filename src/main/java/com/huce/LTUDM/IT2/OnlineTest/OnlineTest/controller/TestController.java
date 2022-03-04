package com.huce.LTUDM.IT2.OnlineTest.OnlineTest.controller;

import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.common.Const;
import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.common.ResponseMessage;
import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.entity.*;
import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.service.*;
import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.sub.entity.SubQuestion;
import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.sub.entity.SubTest;
import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.sub.entity.TestInfo;
import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.util.JwtUtil;
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
    @Autowired
    private JwtUtil jwtTokenUtil;

    //get Test
    @CrossOrigin
    @GetMapping("/{id}")
    public Test getTest(@PathVariable long id) {
        return testService.getTestByTestID(id);
    }

    //submit Test
    @CrossOrigin
    @PutMapping("/submit/{id}")
    public ResponseEntity<?> submitTest(@RequestHeader Map<String, Object> headers, @PathVariable long id, @RequestBody Collection<StudentssAnswer> answers) {
        try {
            String jwt = headers.get(AUTH).toString().substring(7);
            Student student = jwtTokenUtil.getStudentFromToken(jwt);
            Test test = testService.getTestByTestID(id);
            if (test == null) {
                return new ResponseEntity<>(new ResponseMessage(1, "test doesn't exists"), HttpStatus.OK);
            }
            if (!test.getStudent().getStudentCode().equals(student.getStudentCode())) {
                return new ResponseEntity<>(new ResponseMessage(1, "cannot submit"), HttpStatus.OK);
            }
            if (!(test.getStatus().equals(TEST_STT_WAITING) || test.getStatus().equals(TEST_STT_GOING_ON))) {
                return new ResponseEntity<>(new ResponseMessage(1, "permision denied"), HttpStatus.OK);
            }
            test = studentssAnswerService.summitTest(id, answers);
            return new ResponseEntity<>(test, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage(1, e.getMessage()), HttpStatus.CONFLICT);
        }
    }

    //get test by studentID and status of test
    @CrossOrigin
    @GetMapping("/get/{status}")
    public ResponseEntity<?> getTestsByStatus(@RequestHeader Map<String, Object> headers, @PathVariable("status") String status) {
        try {
            String jwt = headers.get(AUTH).toString().substring(7);
            Student student = jwtTokenUtil.getStudentFromToken(jwt);
            List<Test> tests = testService.getTestByStudentIDandStatus(student.getStudentCode(), status);
            for (Test t : tests) {
                t.setRealTime(new Date(System.currentTimeMillis() + 7 * 60 * 60 * 1000));
            }
            return new ResponseEntity<>(tests, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage(1, e.getMessage()), HttpStatus.CONFLICT);
        }
//        return (List<Test>) new ResponseEntity<List<Test>>(testService.getTestByStudentIDandStatus(studentID, status),HttpStatus.OK);
    }

    @CrossOrigin
    @PutMapping("/take-a-test/{id}")
    public ResponseEntity<?> takeATest(@RequestHeader Map<String, Object> headers, @PathVariable("id") long id) {
        try {

            String jwt = headers.get(AUTH).toString().substring(7);
            Student student = jwtTokenUtil.getStudentFromToken(jwt);
            Test test = testService.getTestByTestID(id);

            if (test == null) {
                return new ResponseEntity<>(new ResponseMessage(1, "test not exist"), HttpStatus.OK);
            }
            if (!test.getStudent().getStudentCode().equals(student.getStudentCode())) {
                return new ResponseEntity<>(new ResponseMessage(1, "cannot take a test"), HttpStatus.OK);
            }
            if (!(test.getStatus().equals(TEST_STT_WAITING) ||test.getStatus().equals(TEST_STT_GOING_ON))) {
                return new ResponseEntity<>(new ResponseMessage(1, "permission denied"), HttpStatus.OK);
            }
            if (test.getExam().getStartTime().after(new Date(System.currentTimeMillis() + 7 * 60 * 60 * 1000))) {
                return new ResponseEntity<>(new ResponseMessage(1, "it's not time for the exam yet"), HttpStatus.OK);
            }
            //set start_time and status

            List<SubQuestion> ques = questionssTestService.getSubQuestionTestByTestID(id);
            TestInfo testInfo = new TestInfo(test);
            testInfo.setQuestions(ques);
            if(test.getStatus().equals(TEST_STT_WAITING)) {
                test.setStartTime(new Date(System.currentTimeMillis() + 7 *60 *60 * 1000));
                test.setStatus(TEST_STT_GOING_ON);
                testService.updateTest(id, test);
            }
            return new ResponseEntity<>(testInfo, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ResponseMessage(1, e.getMessage()), HttpStatus.CONFLICT);
        }
    }

    @CrossOrigin
    @GetMapping("/get/own/{status}")
    public ResponseEntity<?> getOwnTest(@RequestHeader Map<String, Object> headers, @PathVariable("status") String status) {
        try {
            String jwt = headers.get(AUTH).toString().substring(7);
            Student student = jwtTokenUtil.getStudentFromToken(jwt);
            List<SubTest> tests = testService.getOwnTestByStatus(student.getStudentCode(), status);
            return new ResponseEntity<>(tests, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ResponseMessage(1, e.getMessage()), HttpStatus.CONFLICT);
        }
    }

    @CrossOrigin
    @GetMapping("/get/own/test/{exam-code}")
    public ResponseEntity<?> getOwnTestByExamCode(@RequestHeader Map<String, Object> headers, @PathVariable("exam-code") String examCode) {
        try {
            String jwt = headers.get(AUTH).toString().substring(7);
            Student student = jwtTokenUtil.getStudentFromToken(jwt);
            SubTest t = testService.getOwnTestByExamCode(student.getStudentCode(), examCode);
            return new ResponseEntity<>(t, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage(1, e.getMessage()), HttpStatus.CONFLICT);
        }
    }
}
