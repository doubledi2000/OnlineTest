package com.huce.LTUDM.IT2.OnlineTest.OnlineTest.controller;

import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.entity.QuestionssTest;
import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.service.QuestionssTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/test")
public class QuestionssTestController {
    @Autowired
    QuestionssTestService questionssTestService;

    @GetMapping("/question/{id}")
    public ResponseEntity<Collection<QuestionssTest>> getQuestionssTest(@PathVariable long id){
        Collection<QuestionssTest> list = questionssTestService.getQuestionByTestID(id);
        return new ResponseEntity<>(list, HttpStatus.FOUND);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getTest(@PathVariable long id){

        return new ResponseEntity<>(questionssTestService.getQuestionssTestByQuestionID(id), HttpStatus.FOUND);
    }
}
