package com.huce.LTUDM.IT2.OnlineTest.OnlineTest.sub.entity;

import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.entity.Question;
import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.entity.QuestionssTest;
import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.entity.StudentssAnswer;

import java.util.ArrayList;
import java.util.List;

public class SubQuestion {
    private long id;
    private String title;
    private String image;
    private int type;
    private List<SubAnswer> answers;

    public SubQuestion() {
    }
    public SubQuestion(QuestionssTest question) {
        answers = new ArrayList<>();
        this.id = question.getId();
        this.title = question.getTitle();
        this.image = question.getImage();
        this.type = question.getType();

        for(StudentssAnswer s: question.getStudentssAnswers()){
            SubAnswer sub = new SubAnswer(s);
            answers.add(sub);
        }

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<SubAnswer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<SubAnswer> answers) {
        this.answers = answers;
    }

    public void addAnswer(long questionID) {

    }

}
