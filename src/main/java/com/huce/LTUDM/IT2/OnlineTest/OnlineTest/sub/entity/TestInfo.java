package com.huce.LTUDM.IT2.OnlineTest.OnlineTest.sub.entity;

import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.entity.Test;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestInfo {
    private long id;
    private Date start;
    private Date end_test;
    private int time;
    private Date real_time;
    private List<SubQuestion> questions;

    public TestInfo() {
    }

    public TestInfo(Test test) {
        questions = new ArrayList<>();
        this.id = test.getId();
        this.start = test.getStartTime();
        this.end_test = test.getExam().getFinalTime();
        this.time = test.getTime();
        this.real_time = new Date(System.currentTimeMillis() + 7 * 60 * 60 * 1000);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd_test() {
        return end_test;
    }

    public void setEnd_test(Date end_test) {
        this.end_test = end_test;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Date getReal_time() {
        return real_time;
    }

    public void setReal_time(Date real_time) {
        this.real_time = real_time;
    }

    public List<SubQuestion> getQuestions() {
        return questions;
    }

    public void setQuestions(List<SubQuestion> questions) {
        this.questions = questions;
    }
}
