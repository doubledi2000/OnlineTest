package com.huce.LTUDM.IT2.OnlineTest.OnlineTest.service;

import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.entity.Answer;
import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.entity.StudentssAnswer;

import java.util.Collection;

public interface StudentssAnswerService {
    public abstract void createStudentssAnswer(StudentssAnswer answer);

    public abstract void updateStudentssAnswer(long id, StudentssAnswer answer);

    public abstract void deleteStudentssAnswer(long id);

    public abstract Collection<StudentssAnswer> getStudentssAnswerByQuestionID(long id);

}
