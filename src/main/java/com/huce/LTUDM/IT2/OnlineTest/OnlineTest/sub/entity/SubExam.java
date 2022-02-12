package com.huce.LTUDM.IT2.OnlineTest.OnlineTest.sub.entity;

public class SubExam {
    private String examCode;
    private String title;
    private String professor;
    private String status;

    public SubExam() {
    }

    public SubExam(String examCode, String title, String professor, String status) {
        this.examCode = examCode;
        this.title = title;
        this.professor = professor;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getExamCode() {
        return examCode;
    }

    public void setExamCode(String examCode) {
        this.examCode = examCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
