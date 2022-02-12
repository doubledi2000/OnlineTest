package com.huce.LTUDM.IT2.OnlineTest.OnlineTest.service;

import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.entity.Answer;
import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.entity.Exam;
import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.sub.entity.SubExam;

import java.util.Collection;

public interface ExamService {
    public abstract void createExam(Exam exam);

    public abstract void updateExam(String id, Exam exam);

    public abstract void deleteExam(String id);

    public abstract Collection<Exam> getExamByProfessorID(String id);

    public abstract Collection<Exam> getAll();

    public abstract Exam getExamByID(String id);

    public abstract Object getExamByStudentCodeAndExamID(String sc, String ex);

    public abstract SubExam getExamWithStatus(String studentCode, String ec);
}
