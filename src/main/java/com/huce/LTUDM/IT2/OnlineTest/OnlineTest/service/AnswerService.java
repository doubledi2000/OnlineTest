package com.huce.LTUDM.IT2.OnlineTest.OnlineTest.service;

import java.util.Collection;

import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.entity.Answer;

public interface AnswerService {
	public abstract void createAnswer(Answer ans);

	public abstract void updateAnswer(long id, Answer ans);

	public abstract void deleteAnswer(long id);

	public abstract Collection<Answer> getAnswerByQuestionID(long id);

	public abstract Collection<Answer> getAnswerByExamID(String id);

}
