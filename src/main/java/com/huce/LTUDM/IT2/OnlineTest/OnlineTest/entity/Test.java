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
	

}
