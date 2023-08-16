package com.epam.project.dao;

import java.util.List;

import com.epam.project.entity.Question;

public interface QuestionsDaoInterface {

	public Question insert(final Question question);

	public Question update(Question question);

	public Question remove(Question question);

	public Question view(int id);

	public List<Question> viewAll();

}
