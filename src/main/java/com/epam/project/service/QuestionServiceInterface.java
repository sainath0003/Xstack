package com.epam.project.service;

import java.util.List;

import com.epam.project.dto.QuestionDto;
import com.epam.project.entity.Question;

public interface QuestionServiceInterface {

	public QuestionDto add(QuestionDto questiondto);

	public QuestionDto update(QuestionDto questiondto);

	public QuestionDto remove(Integer id);

	public QuestionDto view(Integer id);

	public List<Question> viewAll();

}
