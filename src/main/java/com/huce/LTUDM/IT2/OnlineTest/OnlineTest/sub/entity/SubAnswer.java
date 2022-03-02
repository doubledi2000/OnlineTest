package com.huce.LTUDM.IT2.OnlineTest.OnlineTest.sub.entity;

import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.entity.StudentssAnswer;

public class SubAnswer {
    private long id;
    private String content;
    private Boolean your_choose;

    public SubAnswer() {
    }

    public SubAnswer(StudentssAnswer answer){
        this.id = answer.getId();
        this.content = answer.getContent();
        this.your_choose = answer.getChoose();
    }
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

    public Boolean getYour_choose() {
        return your_choose;
    }

    public void setYour_choose(Boolean your_choose) {
        this.your_choose = your_choose;
    }
}
