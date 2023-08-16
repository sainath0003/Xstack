package com.epam.project.entity;

import java.util.List;

import com.epam.project.dto.QuizDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "quizs", uniqueConstraints = @UniqueConstraint(columnNames = { "title" }))
public class Quiz {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String title;
	private String description;
//	@ElementCollection(targetClass = Question.class)
//	@CollectionTable(name = "quiz_questions_table", joinColumns = @JoinColumn(name = "quiz_id"))
	@ManyToMany(targetEntity = Question.class, fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	private List<Question> questions;
	private int marksPerQuestion;

	public Quiz() {
		super();
	}

	public Quiz(QuizDto quiz) {
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
