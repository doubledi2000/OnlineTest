package com.huce.LTUDM.IT2.OnlineTest.OnlineTest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "test")
public class Test implements Serializable, Comparable<Test> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @Column(name = "start_time", nullable = false)
    private Date startTime;

    @Column(name = "real_time")
    private Date realTime;

    @Column(name = "submition_time", nullable = false)
    private Date submissionTime;

    @Column(name = "number_of_question", nullable = false)
    private int noq;
    @Column(name = "correct_answers", nullable = false)
    private int correctAnswer;
    @Column(name = "score")
    private double score;
    private String status;
    private int time;
    private String professor;

    @ManyToOne
    @JoinColumn(name = "student_code")
    @Enumerated(EnumType.STRING)
    @JsonIgnore
    private Student student;

    @ManyToOne
    @JoinColumn(name = "exam_code")
    @Enumerated(EnumType.STRING)
    @JsonIgnore
    private Exam exam;

    @OneToMany(mappedBy = "test")
    @JsonIgnore
    private List<QuestionssTest> questionss;

    public Date getSubmissionTime() {
        return submissionTime;
    }

    public void setSubmissionTime(Date submissionTime) {
        this.submissionTime = submissionTime;
    }

    public List<QuestionssTest> getQuestionss() {
        return questionss;
    }

    public void setQuestionss(List<QuestionssTest> questionss) {
        this.questionss = questionss;
    }

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
        return submissionTime;
    }

    public void setSubmittionTime(Date submissionTime) {
        this.submissionTime = submissionTime;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }


    @Override
    public int compareTo(Test o) {
        long l = getExam().getStartTime().getTime() - o.getExam().getStartTime().getTime();
        if(l > 0) return 1;
        else if(l==0) return 0;
        else return -1;
    }
}
