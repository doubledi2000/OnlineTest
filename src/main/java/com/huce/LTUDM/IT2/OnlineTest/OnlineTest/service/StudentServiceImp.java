package com.huce.LTUDM.IT2.OnlineTest.OnlineTest.service;

import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.entity.Student;
import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class StudentServiceImp implements StudentService {

    @Autowired
    StudentRepository repo;

    @Override
    public void crateStudent(Student student) {
        repo.save(student);
    }

    @Override
    public void updateStudent(String id, Student student) {
        student.setStudentCode(id);
        repo.save(student);
    }

    @Override
    public void deleteStudent(String id) {
        repo.deleteById(id);
    }

    @Override
    public Collection<Student> getAll() {
        return (Collection<Student>) repo.findAll();
    }

    @Override
    public Student getStudentById(String id) {
        return repo.findById(id).get();
    }

    @Override
    public Student getStudentByUsername(String username) {
        return repo.getStudentByUsername(username);
    }


}
