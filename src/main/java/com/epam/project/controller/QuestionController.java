package com.epam.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.epam.project.commontools.InvalidInputException;
import com.epam.project.dto.QuestionDto;
import com.epam.project.service.QuestionServiceInterface;

import jakarta.persistence.RollbackException;

@Controller
@RequestMapping("/question")
public class QuestionController {

	@Autowired
	QuestionServiceInterface questionService;

	String questionString = "question";
	String message = "message";
	String updatequestion = "updatequestion";

	@GetMapping("/library")
	public String questionlibrary() {

		return "questionwelcomepage";
	}

	@GetMapping("/add")
	public String addquestion() {
		return "createquestion";
	}

	@GetMapping("/delete")
	public String removequestion() {

		return "removequestion";
	}

	@GetMapping("/display")
	public String viewquestion() {

		return "viewquestion";
	}

	@GetMapping("/modify")
	public String updatequestion() {
		return "updatequestionPre";
	}

	@GetMapping("/displayall")
	public ModelAndView getAllQuestions() {

		ModelAndView mv = new ModelAndView();
		mv.setViewName("displayallquestions");
		try {
			mv.addObject("questions", questionService.viewAll());
		} catch (InvalidInputException e) {
			mv.addObject(message, "No Questions Found");
		}
		return mv;
	}

	@GetMapping("/displayquestionforupdate")
	public ModelAndView getQuestionDetails(int id) {

		ModelAndView mv = new ModelAndView();
		mv.setViewName(updatequestion);
		try {
			mv.addObject(questionString, questionService.view(id));
		} catch (InvalidInputException e) {
			mv.addObject(message, "No Question with given id found create the questions");
		}
		return mv;
	}

	@GetMapping("/deletefromdisplay")
	public ModelAndView removequestion(int id) {

		ModelAndView mv = new ModelAndView();

		try {

			questionService.remove(id);
			mv.setViewName("displayallquestions");
			mv.addObject("questions", questionService.viewAll());
			mv.addObject(message, "question removed Sucessfully with id " + id);
		} catch (InvalidInputException | IllegalStateException | RollbackException e) {

			mv.setViewName("removefailure");
			mv.addObject(message, "Question cannot be deleted");
		}

		return mv;
	}

	@PostMapping("/add")
	public ModelAndView createQuestion(QuestionDto questiondto) {

		ModelAndView mv = new ModelAndView();
		mv.setViewName("createquestion");
		try {
			mv.addObject(questionString, questionService.add(questiondto));
			mv.addObject(message, "question created Sucessfully");
		} catch (InvalidInputException e) {
			mv.addObject(message, "question Not created");
		}
		return mv;
	}

	@PostMapping("/modify")
	public ModelAndView updtaeQuestion(QuestionDto questiondto) {

		ModelAndView mv = new ModelAndView();
		mv.setViewName("updatesucess");
		try {
			mv.addObject(questionString, questionService.update(questiondto));
			mv.addObject(message, "question updated Sucessfully");
		} catch (InvalidInputException | RollbackException e) {
			mv.addObject(message, "question not updated");
		}
		return mv;
	}

	@PostMapping("/display")
	public ModelAndView viewquestion(int id) {

		ModelAndView mv = new ModelAndView();
		mv.setViewName("viewquestion");
		try {
			mv.addObject(questionString, questionService.view(id));
			mv.addObject(message, "question Found");
		} catch (InvalidInputException e) {
			mv.addObject(message, "No Question Found");
		}
		return mv;
	}

	@PostMapping("/delete")
	public ModelAndView removequestions(int id) {

		ModelAndView mv = new ModelAndView();

		try {
			questionService.remove(id);
			mv.setViewName("removequestion");
			mv.addObject(message, "question removed Sucessfully with id " + id);
		} catch (InvalidInputException | IllegalStateException | RollbackException e) {

			mv.setViewName("removefailure");
			mv.addObject(message, "Question cannot be deleted");
		}

		return mv;
	}

}
