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

    @CrossOrigin
    @GetMapping("/detail/{id}")
    public ResponseEntity<?> getInfo(@PathVariable String id) {
        Student student = studentService.getStudentById(id);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("my-profile")
    public ResponseEntity<?> getMyProfile(@RequestHeader Map<String, Object> headers) {
        try {
            String jwt = headers.get(AUTH).toString().substring(7);
            return new ResponseEntity<>(jwtTokenUtil.getStudentFromToken(jwt), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage(1, e.getMessage()), HttpStatus.valueOf(501));
        }
    }

    @CrossOrigin
    @GetMapping("/all")
    public ResponseEntity<Collection<Student>> getAll() {
        Collection<Student> list = studentService.getAll();
        return new ResponseEntity<Collection<Student>>(list, HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping("/create")
    public void createStudent(@RequestBody Student student) {
        studentService.crateStudent(student);
    }

    @CrossOrigin
    @PutMapping("/edit")
    public ResponseEntity<?> editStudentProfile(@RequestHeader Map<String, Object> headers, @RequestBody Student newInfo) {
        try{
            String jwt = headers.get(AUTH).toString().substring(7);
            Student student = jwtTokenUtil.getStudentFromToken(jwt);
            newInfo.setStudentCode(student.getStudentCode());
            newInfo.setUsername(student.getUsername());
            newInfo.setRole(student.getRole());
            studentService.updateStudent(student.getStudentCode(), newInfo);
            return new ResponseEntity<>(new ResponseMessage(0, "edited"), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage(1, e.getMessage()), HttpStatus.OK);
        }
    }

    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), MD5Converter.StringToMd5(authenticationRequest.getPassword()))
            );

            final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

            final String jwt = jwtTokenUtil.generateToken(userDetails);
            return new ResponseEntity<>(new AuthenticationResponse(0, jwt), HttpStatus.OK);
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new AuthenticationResponse(1, null), HttpStatus.UNAUTHORIZED);
        }

    }

    @CrossOrigin
    @GetMapping("/login")
    public String getLogin() {
        return "Login to system";
    }

    @CrossOrigin
    @PostMapping("sign-up")
    public ResponseEntity<?> signup(@RequestBody Student student) throws Exception {
        try {
            student.setPassword(MD5Converter.StringToMd5(student.getPassword()));
            studentService.crateStudent(student);

            return new ResponseEntity<>(new ResponseMessage(0, "add ok"), HttpStatus.valueOf(201));
        } catch (NoSuchAlgorithmException e) {
            return new ResponseEntity<>(new ResponseMessage(1, e.getMessage()), HttpStatus.FORBIDDEN);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage(1, e.getMessage()), HttpStatus.CONFLICT);
        }

    }
}
