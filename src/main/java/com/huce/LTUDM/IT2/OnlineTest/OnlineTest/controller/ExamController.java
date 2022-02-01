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
    @GetMapping("/id")
    public ResponseEntity<Exam> getExamByID(String id){
        Exam exam = examService.getExamByID(id);
        if(exam != null )
            return new ResponseEntity<Exam>(examService.getExamByID(id), HttpStatus.OK);
        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }
    @PostMapping("/create")
    public ResponseMessage createExam(@RequestBody Exam exam){
        try {
            Professor professor = professorService.getProfessorByID("pr_01");
            exam.setProfessor(professor);
            return new ResponseMessage(0,"add ok");
        }catch (Exception e){
            return new ResponseMessage(1, e.getMessage());
        }
    }
    @PutMapping("/edit/{examCode}")
    public  ResponseMessage editExam(@PathVariable("examCode") String examCode, @RequestBody Exam newExam){
        try {
            Exam exam = examService.getExamByID(examCode);
            if(exam == null){
                return new ResponseMessage(1, "Exam doesn't exist");
            }
            examService.updateExam(examCode,newExam);
            return new ResponseMessage(0,"edit ok");
        }catch (Exception e){
            return new ResponseMessage(1, e.getMessage());
        }
    }
    @GetMapping("/get-own-exam")
    public ResponseEntity<Collection<Exam>> getOwn(){
        Collection<Exam> exams = examService.getExamByProfessorID("pr_01");
        return new ResponseEntity<>(exams,HttpStatus.OK);
    }

    //join test
    @PostMapping("/join/{id}")
    public void joinTest(@RequestHeader Map<String, String> headers, @PathVariable("id") String id){
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
    }


}
