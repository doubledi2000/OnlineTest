package com.huce.LTUDM.IT2.OnlineTest.OnlineTest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "studentss_answer")
public class StudentssAnswer implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String content;
    @JsonIgnore
    private boolean true_false;
    @Column(name = "your_choose")
    private boolean choose;
    @JsonIgnore
    @Column(name = "answer_id")
    private long answerId;
    @ManyToOne
    @JoinColumn(name = "question_id")
    @JsonIgnore
    private QuestionssTest question;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isTrue_false() {
        return true_false;
    }

    public long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(long answerId) {
        this.answerId = answerId;
    }

    public void setTrue_false(boolean true_false) {
        this.true_false = true_false;
    }

    public boolean isChoose() {
        return choose;
    }

    public QuestionssTest getQuestion() {
        return question;
    }

    public void setQuestion(QuestionssTest question) {
        this.question = question;
    }

    public boolean getChoose() {
        return choose;
    }

    public void setChoose(boolean choose) {
        this.choose = choose;
    }

}
