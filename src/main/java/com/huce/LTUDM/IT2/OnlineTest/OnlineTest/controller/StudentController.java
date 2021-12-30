package com.huce.LTUDM.IT2.OnlineTest.OnlineTest.controller;

import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.entity.Student;
import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.entity.Test;
import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.service.StudentService;
import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;
    @GetMapping("/detail/{id}")
    public Student getInfo(@PathVariable String id){
        Student student = studentService.getStudentById(id);
        return student;
    }
    @GetMapping("/all")
    public  ResponseEntity<Collection<Student>> getAll(){
        Collection<Student> list = studentService.getAll();
        return new ResponseEntity<Collection<Student>>(list, HttpStatus.FOUND);
    }
}
