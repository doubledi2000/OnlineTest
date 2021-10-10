package com.huce.LTUDM.IT2.OnlineTest.OnlineTest.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "exam")
public class Exam implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "exam_code")
	private String examCode;
	private String title;
	@Column(name = "start")
	private Date startTime;
	@Column(name = "final")
	private Date finalTime;
	private int status;

	@ManyToOne
	@JoinColumn(name = "professor")
	private Professor professor;

	@OneToMany(mappedBy = "exam")
	private List<Question> question;

	@OneToMany(mappedBy = "exam")
	private List<Test> tests;

	public String getExamCode() {
		return examCode;
	}

	public void setExamCode(String examCode) {
		this.examCode = examCode;
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

	public Date getFinalTime() {
		return finalTime;
	}

	public void setFinalTime(Date finalTime) {
		this.finalTime = finalTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public List<Question> getQuestionList() {
		return question;
	}

	public void setQuestionList(List<Question> questionList) {
		this.question = questionList;
	}

	public List<Test> getTests() {
		return tests;
	}

	public void setTests(List<Test> tests) {
		this.tests = tests;
	}

}