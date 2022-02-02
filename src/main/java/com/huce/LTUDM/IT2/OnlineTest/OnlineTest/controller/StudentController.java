package com.huce.LTUDM.IT2.OnlineTest.OnlineTest.controller;

import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.common.Const;
import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.common.ResponseMessage;
import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.config.AuthenticationRequest;
import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.config.AuthenticationResponse;
import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.config.MD5Converter;
import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.entity.Student;
import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.entity.Test;
import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.service.StudentService;
import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.service.TestService;
import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/student")
public class StudentController implements Const {
    @Autowired
    StudentService studentService;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtUtil jwtTokenUtil;

    @GetMapping("/detail/{id}")
    public Student getInfo(@PathVariable String id){
        Student student = studentService.getStudentById(id);
        return student;
    }
    @GetMapping("my-profile")
    public Student getMyProfile(@RequestHeader Map<String, Object> headers) {
        String jwt = headers.get(AUTH).toString().substring(7);
        return jwtTokenUtil.getStudentFromToken(jwt);
    }
    @GetMapping("/all")
    public  ResponseEntity<Collection<Student>> getAll(){
        Collection<Student> list = studentService.getAll();
        return new ResponseEntity<Collection<Student>>(list, HttpStatus.FOUND);
    }
    @PostMapping("/create")
    public void createStudent(@RequestBody Student student){
        studentService.crateStudent(student);
    }
    @PatchMapping("/edit")
    public void editStudentProfile(@RequestBody Student newInfo){
        Student oldInfo = studentService.getStudentById("0301");
        newInfo.setStudentCode("0301");
        newInfo.setUsername(oldInfo.getUsername());
        newInfo.setRole(oldInfo.getRole());
        studentService.updateStudent("0101", newInfo);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), MD5Converter.StringToMd5(authenticationRequest.getPassword()))
            );

            final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

            final String jwt = jwtTokenUtil.generateToken(userDetails);
            return new ResponseEntity<>(new AuthenticationResponse(0,jwt), HttpStatus.FOUND);
        }catch (BadCredentialsException e) {
            return new ResponseEntity<>(new AuthenticationResponse(1, null), HttpStatus.UNAUTHORIZED);
        }

    }
    @PostMapping("sign-up")
    public ResponseEntity<?> signup(@RequestBody Student student) throws Exception {
        try {
            student.setPassword(MD5Converter.StringToMd5(student.getPassword()));
            studentService.crateStudent(student);

            return new ResponseEntity<>(new ResponseMessage(0, "add ok"), HttpStatus.valueOf(201));
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }catch (Exception e) {

        }
        return new ResponseEntity<>(new ResponseMessage(1, "failed to create student"), HttpStatus.CONFLICT);
    }
}
