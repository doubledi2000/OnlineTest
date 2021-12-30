package com.huce.LTUDM.IT2.OnlineTest.OnlineTest.controller;

import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.entity.Exam;
import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/exam")
public class ExamController {
    @Autowired
    ExamService examService;

    @RequestMapping("/id")
    public ResponseEntity<Exam> getExamByID(String id){
        Exam exam = examService.getExamByID(id);
        if(exam != null )
            return new ResponseEntity<Exam>(examService.getExamByID(id), HttpStatus.FOUND);
        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }




}
