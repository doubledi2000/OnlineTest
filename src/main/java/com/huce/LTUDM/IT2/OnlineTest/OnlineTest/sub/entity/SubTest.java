package com.huce.LTUDM.IT2.OnlineTest.OnlineTest.sub.entity;

import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.entity.Test;

import java.util.Date;

public class SubTest {
    private long id;
    private String professor;
    private int time;
    private Date startTime;
    private Date realTime;
    private Date submissionTime;
    private int noq;
    private int correct_answers;
    private double score;
    private String status;
    private String examCode;
    private String title;
    private Date startTest;
    private Date endTest;

    public SubTest() {
    }

    public SubTest(long id, String professor, int time, Date start_time, Date realTime, Date submissionTime, int noq, int correct_answers, double score, String status, String examCode, String title) {
        this.id = id;
        this.professor = professor;
        this.time = time;
        this.startTime = start_time;
        this.realTime = realTime;
        this.submissionTime = submissionTime;
        this.noq = noq;
        this.correct_answers = correct_answers;
        this.score = score;
        this.status = status;
        this.examCode = examCode;
        this.title = title;
    }
    public SubTest(Test t){
        this.id = t.getId();
        this.professor = t.getProfessor();
        this.time = t.getTime();
        this.startTest = t.getExam().getStartTime();
        this.realTime = new Date(System.currentTimeMillis() + 7 * 60 * 60 * 1000);
        this.submissionTime = t.getSubmissionTime();
        this.noq = t.getNoq();
        this.correct_answers = t.getCorrectAnswer();
        this.score = t.getScore();
        this.status = t.getStatus();
        this.examCode = t.getExam().getExamCode();
        this.title = t.getTitle();
        this.endTest = t.getExam().getFinalTime();

    }


    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getStartTest() {
        return startTest;
    }

    public void setStartTest(Date startTest) {
        this.startTest = startTest;
    }

    public Date getEndTest() {
        return endTest;
    }

    public void setEndTest(Date endTest) {
        this.endTest = endTest;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Date getStart_time() {
        return startTime;
    }

    public void setStart_time(Date start_time) {
        this.startTime = start_time;
    }

    public Date getRealTime() {
        return realTime;
    }

    public void setRealTime(Date realTime) {
        this.realTime = realTime;
    }

    public Date getSubmissionTime() {
        return submissionTime;
    }

    public void setSubmissionTime(Date submissionTime) {
        this.submissionTime = submissionTime;
    }

    public int getNoq() {
        return noq;
    }

    public void setNoq(int noq) {
        this.noq = noq;
    }

    public int getCorrect_answers() {
        return correct_answers;
    }

    public void setCorrect_answers(int correct_answers) {
        this.correct_answers = correct_answers;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getExamCode() {
        return examCode;
    }

    public void setExamCode(String examCode) {
        this.examCode = examCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
