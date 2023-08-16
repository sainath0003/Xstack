package com.epam.project.service;

import java.util.List;

import com.epam.project.dto.QuizDto;
import com.epam.project.entity.Quiz;

public interface QuizServiceInterface {

	public QuizDto add(QuizDto quiz);

	public QuizDto update(QuizDto quiz);

	public QuizDto remove(Integer id);

	public QuizDto view(Integer id);

	public List<Quiz> viewAll();

	public int checkAnswers(String answers, Integer id);

	public QuizDto removeQuestionFromQuiz(int questionid, int quizid);

	public QuizDto addQuestionToQuiz(int questionid, int quizid);

}
