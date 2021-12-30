package com.huce.LTUDM.IT2.OnlineTest.OnlineTest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "test")
public class Test implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "title",nullable = false, length = 255)
    private String title;

    @Column(name = "start_time", nullable = false)
    private Date startTime;

    @Column(name = "real_time")
    private Date realTime;

    @Column(name = "submittion_time", nullable = false)
    private Date submittionTime;

    @Column(name = "number_of_question", nullable = false)
    private int noq;
    @Column(name = "correct_answers", nullable = false)
    private int correctAnswer;
    @Column(name = "score")
    private double score;
    private int status;
    private int time;

    @ManyToOne
    @JoinColumn(name = "student_code")
    @Enumerated(EnumType.STRING)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "exam_code")
    @Enumerated(EnumType.STRING)
    @JsonIgnore
    private Exam exam;

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Date getRealTime() {
        return realTime;
    }

    public void setRealTime(Date realTime) {
        this.realTime = realTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getSubmittionTime() {
        return submittionTime;
    }

    public void setSubmittionTime(Date submittionTime) {
        this.submittionTime = submittionTime;
    }

    public int getNoq() {
        return noq;
    }

    public void setNoq(int noq) {
        this.noq = noq;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }
}
