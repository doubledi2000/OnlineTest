package com.huce.LTUDM.IT2.OnlineTest.OnlineTest.service;

import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.entity.Test;
import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
public class TestServiceImp implements TestService {

    @Autowired
    private TestRepository repo;

    @Override
    public void createTest(Test test) {
        repo.save(test);
    }

    @Override
    public void updateTest(long id, Test test) {
        test.setId(id);
        repo.save(test);
    }

    @Override
    public void deleteTest(long id) {
        repo.deleteById(id);
    }

    @Override
    public Collection<Test> getTestByExamID(String id) {
        return repo.getTestInExam(id);
    }

    @Override
    public Collection<Test> getTestByStudentID(String id) {
        Collection<Test> tests = repo.getTestByStudentID(id);
        for(Test t : tests){
            t.setRealTime(new Date());
        }
        return tests;
    }

    @Override
    public Test getTestByTestID(long id) {
        Test test =repo.findById(id).get();
        test.setRealTime(new Date());
        return test;
    }

    @Override
    public List<Test> getTestByStudentIDandStatus(String id, int stt) {
        List<Test> tests = repo.getTestByStudentIDandStatus(id, stt);
        for(Test t : tests){
            t.setRealTime(new Date());
        }
        return tests;
    }


}
