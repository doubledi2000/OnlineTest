package com.huce.LTUDM.IT2.OnlineTest.OnlineTest.service;

import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.entity.Student;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class StudentServiceImp implements StudentService {
    @Override
    public void crateStudent(Student student) {

    }

    @Override
    public void updateStudent(String id, Student student) {

    }

    @Override
    public void deleteStudent(String id) {

    }

    @Override
    public Collection<Student> getAll() {
        return null;
    }

    @Override
    public Collection<Student> getStudentInExam(String examCode) {
        return null;
    }
}
