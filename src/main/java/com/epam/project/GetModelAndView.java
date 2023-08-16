package com.epam.project;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
@Component
public class GetModelAndView {

	@Bean
	public ModelAndView getModelAndViewObject() {
		return new ModelAndView();
	}
}
