package com.huce.LTUDM.IT2.OnlineTest.OnlineTest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "questionss_test")
public class QuestionssTest implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    private int type;
    private String image;
    private int result;
    @Column(name = "question_id")
    private long questionId;

    @ManyToOne
    @JoinColumn(name = "test_id")
    @JsonIgnore
    private Test test;

    @OneToMany(mappedBy = "question")
    private List<StudentssAnswer> studentssAnswers;

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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public List<StudentssAnswer> getStudentssAnswers() {
        return studentssAnswers;
    }

    public void setStudentssAnswers(List<StudentssAnswer> studentssAnswers) {
        this.studentssAnswers = studentssAnswers;
    }

}
