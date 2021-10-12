package com.huce.LTUDM.IT2.OnlineTest.OnlineTest.entity;

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
    @Column(name = "true_false")
    private int TF;
    @Column(name = "your_choose")
    private int choose;
    @ManyToOne
    @JoinColumn(name = "question_id")
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

    public int getTF() {
        return TF;
    }

    public void setTF(int tF) {
        TF = tF;
    }

    public QuestionssTest getQuestion() {
        return question;
    }

    public void setQuestion(QuestionssTest question) {
        this.question = question;
    }

    public int getChoose() {
        return choose;
    }

    public void setChoose(int choose) {
        this.choose = choose;
    }

}
