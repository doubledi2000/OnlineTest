package com.huce.LTUDM.IT2.OnlineTest.OnlineTest.controller;

import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.common.Const;
import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.common.ResponseMessage;
import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.entity.Exam;
import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.entity.Professor;
import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.entity.Student;
import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.entity.Test;
import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.service.ExamService;
import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.service.ProfessorService;
import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.service.TestService;
import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/exam")
public class ExamController implements Const {
    @Autowired
    ExamService examService;
    @Autowired
    ProfessorService professorService;
    @Autowired
    private JwtUtil jwtTokenUtil;
    @Autowired
    private TestService testService;
    @CrossOrigin
    @GetMapping("/id")
    public ResponseEntity<Exam> getExamByID(String id){
        Exam exam = examService.getExamByID(id);
        if(exam != null )
            return new ResponseEntity<Exam>(examService.getExamByID(id), HttpStatus.OK);
        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }
    @CrossOrigin
    @PostMapping("/create")
    public ResponseEntity<?> createExam(@RequestBody Exam exam){
        try {
            Professor professor = professorService.getProfessorByID("pr_01");
            exam.setProfessor(professor);
            return new ResponseEntity<>(new ResponseMessage(0,"add ok"), HttpStatus.valueOf(201));
        }catch (Exception e){
            return new ResponseEntity<>(new ResponseMessage(1,e.getMessage()),HttpStatus.valueOf(501));
        }
    }
    @CrossOrigin
    @PutMapping("/edit/{examCode}")
    public  ResponseEntity<?> editExam(@PathVariable("examCode") String examCode, @RequestBody Exam newExam){
        try {
            Exam exam = examService.getExamByID(examCode);
            if(exam == null){
                return new ResponseEntity<>(new ResponseMessage(1, "Exam doesn't exist"), HttpStatus.valueOf(501));
            }
            examService.updateExam(examCode,newExam);
            return new ResponseEntity<>(new ResponseMessage(0,"edit ok"), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new ResponseMessage(1, e.getMessage()), HttpStatus.valueOf(501));
        }
    }
    @CrossOrigin
    @GetMapping("/get-own-exam")
    public ResponseEntity<Collection<Exam>> getOwn(){
        Collection<Exam> exams = examService.getExamByProfessorID("pr_01");
        return new ResponseEntity<>(exams,HttpStatus.OK);
    }

    //join test
    @CrossOrigin
    @PostMapping("/join/{id}")
    public ResponseEntity<?> joinTest(@RequestHeader Map<String, String> headers, @PathVariable("id") String id){
        try {
            String jwt = headers.get(AUTH).toString().substring(7);
            Student student = jwtTokenUtil.getStudentFromToken(jwt);
            Exam exam = examService.getExamByID(id);
            Test test = new Test();
            if(exam.getStatus().equals(EXAM_STT_APPROVED)) {
                test.setTitle(exam.getTitle());
                test.setStatus(TEST_STT_WAITING);
                test.setStartTime(exam.getStartTime());
                test.setRealTime(new Date());
                test.setSubmittionTime(new Date());
                test.setNoq(exam.getQuestionList().size());
                test.setCorrectAnswer(0);
                test.setScore(0);
                test.setStudent(student);
                test.setExam(exam);
                test.setTime(90);
                testService.createTest(test);
                testService.generateQuestion(test.getId());
            }
            return new ResponseEntity<>(new ResponseMessage(0,"joined"), HttpStatus.valueOf(201));
        }catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage(1,e.getMessage()), HttpStatus.valueOf(501));
        }
    }


}
