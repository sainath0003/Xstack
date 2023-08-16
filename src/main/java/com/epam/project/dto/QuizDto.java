package com.epam.project.dto;

import java.util.List;

import com.epam.project.entity.Question;
import com.epam.project.entity.Quiz;

public class QuizDto {
	private int id;
	private String title;
	private String description;
	private List<Question> questions;
	private int marksPerQuestion;

	public QuizDto() {
		super();
	}

	public QuizDto(Quiz quiz) {
		this.id = quiz.getId();
		this.title = quiz.getTitle();
		this.description = quiz.getDescription();
		this.questions = quiz.getQuestions();
		this.marksPerQuestion = quiz.getMarksPerQuestion();

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public int getMarksPerQuestion() {
		return marksPerQuestion;
	}

	public void setMarksPerQuestion(int marksPerQuestion) {
		this.marksPerQuestion = marksPerQuestion;
	}

	@Override
	public String toString() {

		return "\nQuiz Details:\nid = " + id + ",\ntitle = " + title + ",\ndescription = " + description
				+ ",\nquestions = " + questions + ",\nmarksPerQuestion = " + marksPerQuestion + ",\n\n";
	}

}
