package com.epam.project.entity;

import java.util.List;

import com.epam.project.dto.QuestionDto;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity

@Table(name = "questions", uniqueConstraints = @UniqueConstraint(columnNames = { "title" }))

public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String title;
	private String description;
	private int numberOfOptions;
	@ElementCollection
	@CollectionTable(name = "options_table", joinColumns = @JoinColumn(name = "question_id"))
	private List<String> options;
	private String difficulty;
	@ElementCollection
	@CollectionTable(name = "topics_table", joinColumns = @JoinColumn(name = "question_id"))
	private List<String> taggingTopics;

	private int answer;

	public Question() {
		super();
	}

	public Question(Question question) {
		super();
		this.id = question.id;
		this.title = question.title;
		this.description = question.description;
		this.numberOfOptions = question.numberOfOptions;
		this.options = question.options;
		this.difficulty = question.difficulty;
		this.taggingTopics = question.taggingTopics;
		this.answer = question.answer;
	}

	public Question(QuestionDto question) {
		super();
		this.id = question.getId();
		this.title = question.getTitle();
		this.description = question.getDescription();
		this.numberOfOptions = question.getNumberOfOptions();
		this.options = question.getOptions();
		this.difficulty = question.getDifficulty();
		this.taggingTopics = question.getTaggingTopics();
		this.answer = question.getAnswer();
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

	public int getNumberOfOptions() {
		return numberOfOptions;
	}

	public void setNumberOfOptions(int numberOfOptions) {
		this.numberOfOptions = numberOfOptions;
	}

	public List<String> getOptions() {
		return options;
	}

	public void setOptions(List<String> options) {
		this.options = options;
	}

	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	public List<String> getTaggingTopics() {
		return taggingTopics;
	}

	public void setTaggingTopics(List<String> taggingTopics) {
		this.taggingTopics = taggingTopics;
	}

	public int getAnswer() {
		return answer;
	}

	public void setAnswer(int answer) {
		this.answer = answer;
	}

	@Override
	public String toString() {
		return "\nQuestion Details:id =" + id + "\ntitle = " + title + ",\ndescription = " + description
				+ ",\nnumberOfOptions = " + numberOfOptions + ",\noptions = " + options + ",\ndifficulty = "
				+ difficulty + ",\ntaggingTopics = " + taggingTopics + ",\nanswer = " + answer + "\n";
	}

}
