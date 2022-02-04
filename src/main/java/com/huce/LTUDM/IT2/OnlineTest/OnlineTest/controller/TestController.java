package com.huce.LTUDM.IT2.OnlineTest.OnlineTest.controller;

import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.common.Const;
import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.common.ResponseMessage;
import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.entity.*;
import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.service.*;
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
    public Test getTest(@PathVariable long id){
        return testService.getTestByTestID(id);
    }

    //submit Test
    @CrossOrigin
    @PutMapping("submit/{id}")
    public ResponseEntity<?> submitTest(@RequestHeader Map<String, Object> headers,@PathVariable long id, @RequestBody Collection<StudentssAnswer> answers){
        String jwt = headers.get(AUTH).toString().substring(7);
        Student student = jwtTokenUtil.getStudentFromToken(jwt);
        Test test = testService.getTestByTestID(id);
        if(!test.getStudent().getStudentCode().equals(student.getStudentCode())) {
            return new ResponseEntity<>(new ResponseMessage(1, "cannot submit"), HttpStatus.FORBIDDEN);
        }
        studentssAnswerService.summitTest(1l, answers);
        return new ResponseEntity<>(testService.getTestByTestID(1l), HttpStatus.OK);
    }

    //get test by studentID and status of test
    @CrossOrigin
    @GetMapping("/get/{status}")
    public ResponseEntity<?> getTestsByStatus(@RequestHeader Map<String, Object> headers,@PathVariable("status") String status){
        try {
            String jwt = headers.get(AUTH).toString().substring(7);
            Student student = jwtTokenUtil.getStudentFromToken(jwt);
            List<Test> tests = testService.getTestByStudentIDandStatus(student.getStudentCode(), status);
            for (Test t : tests) {
                t.setRealTime(new Date());
            }
            return new ResponseEntity<>(tests, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage(1, e.getMessage()), HttpStatus.FORBIDDEN);
        }
//        return (List<Test>) new ResponseEntity<List<Test>>(testService.getTestByStudentIDandStatus(studentID, status),HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("take-a-test/{id}")
    public ResponseEntity<?> takeATest(@RequestHeader Map<String, Object> headers,@PathVariable("id") long id) {
        String jwt = headers.get(AUTH).toString().substring(7);
        Student student = jwtTokenUtil.getStudentFromToken(jwt);
        Test test = testService.getTestByTestID(id);
        if(!test.getStudent().getStudentCode().equals(student.getStudentCode())) {
            return new ResponseEntity<>(new ResponseMessage(1,"cannot take a test"), HttpStatus.FORBIDDEN);
        }
        List<QuestionssTest> ques = questionssTestService.getQuestionByTestID(1);

        return new ResponseEntity<>(ques,HttpStatus.OK);
    }
}
