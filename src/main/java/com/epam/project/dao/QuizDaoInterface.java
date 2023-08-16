package com.epam.project.dao;

import java.util.List;

import com.epam.project.entity.Quiz;

public interface QuizDaoInterface {

	public Quiz insert(final Quiz quiz);

	public Quiz update(Quiz quiz);

	public Quiz remove(Quiz quiz);

	public Quiz view(int id);

	public List<Quiz> viewAll();

}
