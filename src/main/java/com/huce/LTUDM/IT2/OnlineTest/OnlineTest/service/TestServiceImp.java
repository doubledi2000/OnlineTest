package com.huce.LTUDM.IT2.OnlineTest.OnlineTest.service;

import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.entity.Test;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class TestServiceImp implements TestService {
    @Override
    public void createTest(Test test) {

    }

    @Override
    public void updateTest(long id, Test test) {

    }

    @Override
    public void deleteTest(long id) {

    }

    @Override
    public Collection<Test> getTestByExamID(String id) {
        return null;
    }

    @Override
    public Collection<Test> getTestByStudentID(String id) {
        return null;
    }
}
