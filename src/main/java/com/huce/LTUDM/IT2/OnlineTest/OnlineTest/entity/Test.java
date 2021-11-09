package com.huce.LTUDM.IT2.OnlineTest.OnlineTest.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "test")
public class Test implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String title;
    @Column(name = "start_time")
    private Date startTime;
    @Column(name = "submittion_time")
    private Date submittionTime;
    @Column(name = "number_of_question")
    private int noq;
    @Column(name = "correct_answers")
    private int correctAnswer;
    private double score;
    private int status;

    @ManyToOne
    @JoinColumn(name = "student_code")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "exam_code")
    private Exam exam;

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
