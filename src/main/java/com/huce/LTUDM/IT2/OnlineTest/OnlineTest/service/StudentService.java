package com.huce.LTUDM.IT2.OnlineTest.OnlineTest.service;

import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.entity.Answer;
import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.entity.Student;

import java.util.Collection;

public interface StudentService {
    public abstract void crateStudent(Student student);

    public abstract void updateStudent(String id, Student student);

    public abstract void deleteStudent(String id);

    public abstract Collection<Student> getAll();

    public abstract Student getStudentById(String id);

    public abstract Student getStudentByUsername(String username);
}

